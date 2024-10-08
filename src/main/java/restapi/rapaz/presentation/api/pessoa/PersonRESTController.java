package restapi.rapaz.presentation.api.pessoa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.rapaz.infra.application_service.crud.person.CrudServicePerson;
import restapi.rapaz.infra.exception.ApplicationException;
import restapi.rapaz.infra.use_case.crud.pessoa.*;
import restapi.rapaz.presentation.api.CRUD;
import restapi.rapaz.presentation.api.exception.ApiException;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonRESTController implements CRUD<PersonRequestHTTP, PersonResponseHTTP> {

    private final CrudServicePerson crud;

    @Override
    public ResponseEntity<PersonResponseHTTP> create(PersonRequestHTTP dto) {
        PersonInsert personToSave = new PersonInsert(dto.nome(), dto.sobrenome(), dto.idade(), dto.email());
        PersonSaved personSaved;
        try {
            personSaved = crud.save(personToSave);
        } catch (ApplicationException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return ResponseEntity.ok(new PersonResponseHTTP(personSaved.id(), personSaved.nome(),
                personSaved.sobrenome(), personSaved.idade(), personSaved.email()));
    }

    @Override
    public ResponseEntity<PersonResponseHTTP> read(String id) {
        UUID uid = UUID.fromString(id);
        PersonFound personFound = crud.findById(uid);
        return ResponseEntity.ok(new PersonResponseHTTP(personFound.id(), personFound.nome(),
                personFound.sobrenome(), personFound.idade(), personFound.email()));
    }

    @Override
    public ResponseEntity<Collection<PersonResponseHTTP>> readAll() {
        Collection<PersonFound> people = crud.findAll();
        return ResponseEntity.ok(people.stream().map(person -> new PersonResponseHTTP(
                person.id(), person.nome(), person.sobrenome(), person.idade(), person.email()
        )).toList());
    }

    @Override
    public ResponseEntity<PersonResponseHTTP> update(String id, PersonRequestHTTP dto) {
        UUID uid = UUID.fromString(id);
        PersonSetUpdate personToUpdate = new PersonSetUpdate(dto.nome(), dto.email(), dto.sobrenome(), dto.idade());
        PersonUpdated personUpdated;
        try {
            personUpdated = crud.update(uid, personToUpdate);
        } catch (ApplicationException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return ResponseEntity.ok(new PersonResponseHTTP(personUpdated.id(), personUpdated.nome(),
                personUpdated.sobrenome(), personUpdated.idade(), personUpdated.email()));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        UUID uid = UUID.fromString(id);
        crud.deleteById(uid);
        return ResponseEntity.ok().build();
    }

}
