package restapi.rapaz.infra.use_case.crud.pessoa;

import org.springframework.stereotype.Service;
import restapi.rapaz.domain.PersonGateway;

import java.util.UUID;

@Service
public record DeletePerson(PersonGateway repo) {

    public void byId(UUID id) {
        repo.deleteById(id);
    }
}
