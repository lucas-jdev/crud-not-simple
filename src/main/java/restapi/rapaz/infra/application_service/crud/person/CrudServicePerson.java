package restapi.rapaz.infra.application_service.crud.person;

import org.springframework.stereotype.Service;
import restapi.rapaz.infra.application_service.crud.CrudService;
import restapi.rapaz.infra.use_case.crud.pessoa.*;

import java.util.Collection;
import java.util.UUID;

@Service
public record CrudServicePerson(
        SavePerson savePerson,
        FindAllPeople findAllPeople,
        FindPerson findPerson,
        DeletePerson deletePerson,
        UpdatePerson updatePerson
) implements CrudService {

    @Override
    public Collection<PersonFound> findAll() {
        return findAllPeople.execute();
    }

    @Override
    public PersonFound findById(UUID id) {
        return findPerson.byId(id);
    }

    @Override
    public PersonSaved save(PersonInsert person) {
        return savePerson.execute(person);
    }

    @Override
    public void deleteById(UUID id) {
        deletePerson.byId(id);
    }

    @Override
    public PersonUpdated update(UUID id, PersonSetUpdate person) {
        return updatePerson.execute(id, person);
    }

}
