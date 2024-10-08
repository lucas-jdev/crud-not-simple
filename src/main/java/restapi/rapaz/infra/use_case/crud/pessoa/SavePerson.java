package restapi.rapaz.infra.use_case.crud.pessoa;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import restapi.rapaz.domain.PersonGateway;
import restapi.rapaz.domain.exception.BusinessException;
import restapi.rapaz.domain.person.Person;
import restapi.rapaz.infra.exception.ApplicationException;

@Service
public record SavePerson(PersonGateway repo) {

    public PersonSaved execute(@Valid PersonInsert dto) throws ApplicationException {
        var person = new Person();
        try {
            person.setName(dto.nome());
            person.setLastName(dto.sobrenome());
            person.setYear(dto.idade());
            person.setEmail(dto.email());
        } catch (BusinessException e) {
            throw new ApplicationException(e.getMessage());
        }


        repo.save(person);

        return new PersonSaved(person.getId(), person.getName(), person.getEmail(), person.getLastName(), person.getYear());
    }

}
