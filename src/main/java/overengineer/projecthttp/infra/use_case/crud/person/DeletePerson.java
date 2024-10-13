package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.PersonGateway;

import java.util.UUID;

@Service
public record DeletePerson(PersonGateway repo) {

    public void byId(UUID id) {
        repo.deleteById(id);
    }
}
