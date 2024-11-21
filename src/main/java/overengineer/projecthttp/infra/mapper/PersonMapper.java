package overengineer.projecthttp.infra.mapper;

import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.infra.db.PersonEntity;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;
import overengineer.projecthttp.presentation.api.person.PersonResponseHTTP;

import java.time.LocalDate;
import java.time.Period;

public class PersonMapper {

    private PersonMapper() {
        throw new ApplicationException("PersonMapper constructor called");
    }

    public static Person entityToDomain(PersonEntity entity) {
        return new Person(entity.getPublicId(), entity.getName(),
                entity.getLastName(), entity.getEmail(),
                entity.getBirthDate(), entity.isActive());
    }

    public static PersonFound domainToOutputFound(Person person) {
        return new PersonFound(person.getId(), person.getName(),
                person.getLastName(), person.getBirthDate(),
                person.getEmail(), person.isActive());
    }

    public static PersonResponseHTTP outputFoundToResponseHTTP(PersonFound person) {
        Integer year = Period.between(person.birthDate(), LocalDate.now()).getYears();
        return new PersonResponseHTTP(person.id(), person.name(),
                person.lastName(), year, person.email(),
                person.active()
        );
    }

}
