package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.domain.exception.BusinessException;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonSetUpdate;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonUpdated;

import java.util.UUID;

@Service
public record UpdatePerson(PersonGateway repo) {

    public PersonUpdated execute(UUID id, PersonSetUpdate dto) {
        var personContainer = repo.findById(id);
        var person = personContainer.orElseThrow(() -> new ApplicationException("Pessoa not found"));

        try {
            if(dto.name() != null) person.setName(dto.name());
            if(dto.lastName() != null) person.setLastName(dto.lastName());
            if(dto.birthDate() != null) person.setBirthDate(dto.birthDate());
            if(dto.email() != null) person.setEmail(dto.email());
        } catch (BusinessException e) {
            throw new ApplicationException(e.getMessage());
        }

        repo.save(person);
        return new PersonUpdated(person.getId(), person.getName(), person.getEmail(),
                person.getLastName(), person.getYear(), person.isActive());
    }

}
