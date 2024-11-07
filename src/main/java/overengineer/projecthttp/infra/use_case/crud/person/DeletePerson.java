package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.infra.exception.ApplicationException;

import java.util.UUID;

@Service
public record DeletePerson(PersonGateway repo) {

    public void byId(UUID id) {
        if (id == null) {
            throw new ApplicationException("Id cannot be null");
        }
        repo.deleteById(id);
    }
}
