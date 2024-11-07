package overengineer.projecthttp.use_case;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.FindPerson;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FindPersonTest extends AbstractPersonCommonUseCase {

    private static FindPerson findPerson;

    @BeforeAll
    static void setUp() {
        AbstractPersonCommonUseCase.init();
        findPerson = new FindPerson(personGateway);
    }

    @BeforeEach
    void setUpEach() {
        super.setValuesToPerson();
        personGateway.save(person);
    }

    @AfterEach
    void tearDownEach() {
        super.removeAll();
    }

    @Test
    void shouldFindPerson() {
        var personFound = findPerson.byId(person.getId());
        assertNotNull(personFound);
    }

    @Nested
    class Anomalies {

        @ParameterizedTest
        @NullSource
        void shouldNotFindPersonWithNullId(UUID id) {
            assertThrows(ApplicationException.class, () -> findPerson.byId(id));
        }

        @Test
        void shouldNotFindPersonWithInvalidId() {
            var invalidId = UUID.randomUUID();
            assertThrows(ApplicationException.class, () -> findPerson.byId(invalidId));
        }

    }

}
