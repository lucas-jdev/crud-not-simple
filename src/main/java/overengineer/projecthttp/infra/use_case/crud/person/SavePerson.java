package overengineer.projecthttp.infra.use_case.crud.person;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.domain.exception.BusinessException;
import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonInsert;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonSaved;

@Service
@Slf4j
public record SavePerson(PersonGateway repo) {

    public PersonSaved execute(@Valid PersonInsert dto) throws ApplicationException {
        var person = new Person();
        try {
            person.setName(dto.name());
            person.setLastName(dto.lastName());
            person.setBirthDate(dto.birthDate());
            person.setEmail(dto.email());
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }

        repo.save(person);

        return new PersonSaved(person.getId(), person.getName(), person.getEmail(), person.getLastName(), person.getYear());
    }

}
