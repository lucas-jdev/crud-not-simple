package restapi.rapaz.infra.application_service.crud;

import restapi.rapaz.infra.use_case.crud.pessoa.*;

import java.util.Collection;
import java.util.UUID;

public interface CrudService {
    Collection<PersonFound> findAll() ;

    PersonFound findById(UUID id);

    PersonSaved save(PersonInsert person);

    void deleteById(UUID id);

    PersonUpdated update(UUID id, PersonSetUpdate person);
}
