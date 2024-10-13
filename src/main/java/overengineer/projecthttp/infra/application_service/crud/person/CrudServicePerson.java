package overengineer.projecthttp.infra.application_service.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.infra.application_service.crud.CrudService;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.*;
import overengineer.projecthttp.infra.use_case.crud.person.dto.*;

import java.util.Collection;
import java.util.UUID;

@Service
public record CrudServicePerson(
        SavePerson savePerson,
        FindAllPeople findAllPeople,
        FindAllPeopleActives findAllPeopleActives,
        FindPerson findPerson,
        DeletePerson deletePerson,
        UpdatePerson updatePerson
) implements CrudService {

    @Override
    public Collection<PersonFound> findAll() {
        return findAllPeople.execute();
    }

    public Collection<PersonFound> findAllActives() {
        return findAllPeopleActives.execute();
    }

    @Override
    public PersonFound findById(UUID id) {
        return findPerson.byId(id);
    }

    @Override
    public PersonSaved save(PersonInsert person) throws ApplicationException {
        return savePerson.execute(person);
    }

    @Override
    public void deleteById(UUID id) {
        deletePerson.byId(id);
    }

    @Override
    public PersonUpdated update(UUID id, PersonSetUpdate person) throws ApplicationException {
        return updatePerson.execute(id, person);
    }

}
