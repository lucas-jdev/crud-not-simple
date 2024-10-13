package overengineer.projecthttp.infra.application_service.crud;

import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.*;

import java.util.Collection;
import java.util.UUID;

public interface CrudService {
    Collection<PersonFound> findAll() ;

    PersonFound findById(UUID id);

    PersonSaved save(PersonInsert person) throws ApplicationException;

    void deleteById(UUID id);

    PersonUpdated update(UUID id, PersonSetUpdate person) throws ApplicationException;
}
