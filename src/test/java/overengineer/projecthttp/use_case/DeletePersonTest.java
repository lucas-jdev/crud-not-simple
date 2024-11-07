package overengineer.projecthttp.use_case;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.DeletePerson;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

class DeletePersonTest extends AbstractPersonCommonUseCase {

    private static DeletePerson deletePerson;

    @BeforeAll
     static void setUp() {
        AbstractPersonCommonUseCase.init();
        deletePerson = new DeletePerson(personGateway);
    }

    @BeforeEach
    void setUpEach() {
        super.setValuesToPerson();
        personGateway.save(person);
    }

    @Test
    void shouldLogicallyDeletePerson() {
        deletePerson.byId(person.getId());
        Optional<Person> personFound = personGateway.findById(person.getId());
        Person p = personFound.orElse(new Person());
        assertFalse(p.isActive());
    }

    @Nested
    class Anomalies {

        @ParameterizedTest
        @NullSource
        void shouldNotLogicallyDeletePersonWithNullId(UUID id) {
            assertThrows(ApplicationException.class, () -> deletePerson.byId(id));
        }

    }

}
