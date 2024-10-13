package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.PersonGateway;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;

import java.util.Collection;

@Service
public record FindAllPeople(PersonGateway repo){

    public Collection<PersonFound> execute() {
        return repo.findAll()
            .stream()
            .map(person -> new PersonFound(person.getId(), person.getName(),
                    person.getLastName(), person.getBirthDate(),
                    person.getEmail(), person.isActive()))
            .toList();
    }

}
