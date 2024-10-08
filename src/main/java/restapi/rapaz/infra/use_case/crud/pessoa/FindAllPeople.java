package restapi.rapaz.infra.use_case.crud.pessoa;

import org.springframework.stereotype.Service;
import restapi.rapaz.domain.PersonGateway;

import java.util.Collection;

@Service
public record FindAllPeople(PersonGateway repo){

    public Collection<PersonFound> execute() {
        return repo.findAll()
            .stream()
            .map(person -> new PersonFound(person.getId(), person.getName(), person.getLastName(), person.getYear(), person.getEmail()))
            .toList();
    }

}
