package overengineer.projecthttp.presentation.api.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import overengineer.projecthttp.domain.person.PersonFilter;
import overengineer.projecthttp.infra.application_service.crud.person.CrudServicePerson;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.mapper.PersonMapper;
import overengineer.projecthttp.infra.use_case.crud.person.dto.*;
import overengineer.projecthttp.presentation.api.CRUD;
import overengineer.projecthttp.presentation.api.exception.ApiException;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonRESTController implements CRUD<PersonRequestHTTP, PersonResponseHTTP> {

    private final CrudServicePerson crud;
    private final PersonFilter<?> filter;

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
        return PersonMapper.outputFoundToResponseHTTP(personFound);
    }

    @Override
    public Collection<PersonResponseHTTP> readAll(Map<String, Object> queryParams) {
        Collection<PersonFound> people;
        if (queryParams.isEmpty()) {
            people = crud.findAll();
        } else {
            people = crud.findAll(filter, queryParams);
        }
        
        return people.stream()
                .map(PersonMapper::outputFoundToResponseHTTP)
                .toList();
    }

    @GetMapping("/actives")
    public Collection<PersonResponseHTTP> readAllActives() {
        Collection<PersonFound> peopleActives = crud.findAllActives();
        return peopleActives.stream()
                .map(PersonMapper::outputFoundToResponseHTTP)
                .toList();
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
