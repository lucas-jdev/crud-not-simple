package restapi.rapaz.use_case;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import restapi.rapaz.domain.PersonGateway;
import restapi.rapaz.domain.person.Person;
import restapi.rapaz.doubletests.fakes.PersonRepositoryFakeDB;
import restapi.rapaz.infra.exception.ApplicationException;
import restapi.rapaz.infra.use_case.crud.pessoa.PersonInsert;
import restapi.rapaz.infra.use_case.crud.pessoa.PersonSaved;
import restapi.rapaz.infra.use_case.crud.pessoa.SavePerson;

import static org.junit.jupiter.api.Assertions.*;

class SavePersonTest {

    private static PersonGateway personGateway;
    private static SavePerson savePerson;
    private static Faker faker;
    private Person person;

    @BeforeAll
    static void setUp() {
        personGateway = new PersonRepositoryFakeDB();
        savePerson = new SavePerson(personGateway);
        faker = new Faker();
    }

    @BeforeEach
    void setUpEach() {
        person = new Person();
        person.setName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.internet().emailAddress());
        person.setYear(faker.number().numberBetween(18, 80));
    }

    @AfterEach
    void tearDownEach() {
        person = null;
        personGateway.deleteAll();
    }

    @Test
    void shouldSavePerson() {
        PersonSaved personSaved = savePerson.execute(
                new PersonInsert(person.getName(), person.getLastName(), person.getYear(), person.getEmail()));

        assertNotNull(personSaved);
    }

    @Nested
    class Anomalies {

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ",})
        void savePersonWithNameNullShouldBeThrowCustomException(String name) {
            assertThrows(ApplicationException.class, () -> savePerson.execute(
                    new PersonInsert(name, person.getLastName(), person.getYear(), person.getEmail())));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ",})
        void savePersonWithLastNameInvalidShouldBeThrowCustomException(String lastName) {
            assertThrows(ApplicationException.class, () -> savePerson.execute(
                    new PersonInsert(person.getName(), lastName, person.getYear(), person.getEmail())));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"", " ", "john.doe", "john.doe@"})
        void savePersonWithEmailInvalidShouldBeThrowCustomException(String email) {
            assertThrows(ApplicationException.class, () -> savePerson.execute(
                    new PersonInsert(person.getName(), person.getLastName(), person.getYear(), email)));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 81, 17})
        @NullSource
        void savePersonWithYearInvalidShouldBeThrowCustomException(Integer year) {
            assertThrows(ApplicationException.class, () -> savePerson.execute(
                    new PersonInsert(person.getName(), person.getLastName(), year, person.getEmail())));
        }

    }

}
