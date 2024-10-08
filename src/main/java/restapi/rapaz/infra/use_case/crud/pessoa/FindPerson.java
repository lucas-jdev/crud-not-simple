package restapi.rapaz.infra.use_case.crud.pessoa;

import org.springframework.stereotype.Service;
import restapi.rapaz.domain.PersonGateway;
import restapi.rapaz.domain.exception.BusinessException;

import java.util.UUID;

@Service
public record FindPerson(PersonGateway repo) {

    public PersonFound byId(UUID id) {
        var personContainer = repo.findById(id);
        var person = personContainer.orElseThrow(() -> new BusinessException("Pessoa not found"));
        return new PersonFound(person.getId(), person.getName(), person.getLastName(), person.getYear(), person.getEmail());
    }

}
