package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;

import java.util.UUID;

@Service
public record FindPerson(PersonGateway repo) {

    public PersonFound byId(UUID id) {
        if (id == null) {
            throw new ApplicationException("Id cannot be null");
        }
        var personContainer = repo.findById(id);
        var person = personContainer.orElseThrow(() -> new ApplicationException("Pessoa not found"));
        return new PersonFound(person.getId(), person.getName(),
                person.getLastName(), person.getBirthDate(),
                person.getEmail(), person.isActive());
    }

}
