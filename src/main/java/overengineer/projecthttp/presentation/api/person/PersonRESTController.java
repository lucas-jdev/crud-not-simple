package overengineer.projecthttp.presentation.api.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overengineer.projecthttp.infra.application_service.crud.person.CrudServicePerson;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.*;
import overengineer.projecthttp.presentation.api.CRUD;
import overengineer.projecthttp.presentation.api.exception.ApiException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonRESTController implements CRUD<PersonRequestHTTP, PersonResponseHTTP> {

    private final CrudServicePerson crud;

    @Override
    public PersonResponseHTTP create(PersonRequestHTTP dto) {
        PersonInsert personToSave = new PersonInsert(dto.name(), dto.lastName(), dto.birthDate(), dto.email());
        PersonSaved personSaved;
        try {
            personSaved = crud.save(personToSave);
        } catch (ApplicationException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return new PersonResponseHTTP(personSaved.id(), personSaved.name(),
                personSaved.lastName(), personSaved.yearsOld(), personSaved.email(), true);
    }

    @Override
    public PersonResponseHTTP read(String id) {
        UUID uid = UUID.fromString(id);
        PersonFound personFound = crud.findById(uid);
        Integer year = Period.between(personFound.birthDate(), LocalDate.now()).getYears();
        return new PersonResponseHTTP(personFound.id(), personFound.name(),
                personFound.lastName(), year, personFound.email(), personFound.active());
    }

    @Override
    public Collection<PersonResponseHTTP> readAll() {
        Collection<PersonFound> people = crud.findAll();
        return people.stream().map(person -> {
            Integer year = Period.between(person.birthDate(), LocalDate.now()).getYears();
            return new PersonResponseHTTP(
                person.id(), person.name(), person.lastName(), year, person.email(), person.active()
            );
        }).toList();
    }

    @GetMapping("/actives")
    public ResponseEntity<Collection<PersonResponseHTTP>> readAllActives() {
        Collection<PersonFound> peopleActives = crud.findAllActives();
        return ResponseEntity.ok(peopleActives.stream().map(person -> {
            Integer year = Period.between(person.birthDate(), LocalDate.now()).getYears();
            return new PersonResponseHTTP(
                person.id(), person.name(), person.lastName(), year, person.email(), person.active()
            );
        }).toList());
    }

    @Override
    public PersonResponseHTTP update(String id, PersonRequestHTTP dto) {
        UUID uid = UUID.fromString(id);
        PersonSetUpdate personToUpdate = new PersonSetUpdate(dto.name(), dto.email(), dto.lastName(), dto.birthDate());
        PersonUpdated personUpdated;
        try {
            personUpdated = crud.update(uid, personToUpdate);
        } catch (ApplicationException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return new PersonResponseHTTP(personUpdated.id(), personUpdated.name(),
                personUpdated.lastName(), personUpdated.yearsOld(),
                personUpdated.email(), personUpdated.active());
    }

    @Override
    public void delete(String id) {
        UUID uid = UUID.fromString(id);
        crud.deleteById(uid);
    }

}
