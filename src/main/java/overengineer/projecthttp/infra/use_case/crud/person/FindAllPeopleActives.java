package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.PersonGateway;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;

import java.util.Collection;

@Service
public record FindAllPeopleActives(PersonGateway repo) {

    public Collection<PersonFound> execute() {
        return repo.findAllActives()
                .stream()
                .map(person -> new PersonFound(person.getId(), person.getName(),
                        person.getLastName(), person.getBirthDate(),
                        person.getEmail(), person.isActive()))
                .toList();
    }

}
