package overengineer.projecthttp.use_case;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonInsert;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonSaved;
import overengineer.projecthttp.infra.use_case.crud.person.SavePerson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavePersonTest extends AbstractPersonCommonUseCase {

    private static SavePerson savePerson;

    @BeforeAll
    static void setUp() {
        AbstractPersonCommonUseCase.init();
        savePerson = new SavePerson(personGateway);
    }

    @BeforeEach
    void setUpEach() {
        super.setValuesToPerson();
    }

    @AfterEach
    void tearDownEach() {
        super.removeAll();
    }

    @Test
    void shouldSavePerson() {
        PersonSaved personSaved = savePerson.execute(
                new PersonInsert(person.getName(), person.getLastName(), person.getBirthDate(), person.getEmail()));

        assertNotNull(personSaved);
    }

    @Nested
    class Anomalies {

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ",})
        void savePersonWithNameNullShouldBeThrowCustomException(String name) {
            PersonInsert insert = new PersonInsert(name, person.getLastName(), person.getBirthDate(), person.getEmail());
            assertThrows(ApplicationException.class, () -> savePerson.execute(insert));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ",})
        void savePersonWithLastNameInvalidShouldBeThrowCustomException(String lastName) {
            PersonInsert insert = new PersonInsert(person.getName(), lastName, person.getBirthDate(), person.getEmail());
            assertThrows(ApplicationException.class, () -> savePerson.execute(insert));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ", "john.doe", "john.doe@"})
        void savePersonWithEmailInvalidShouldBeThrowCustomException(String email) {
            PersonInsert insert = new PersonInsert(person.getName(), person.getLastName(), person.getBirthDate(), email);
            assertThrows(ApplicationException.class, () -> savePerson.execute(insert));
        }

    }

}
