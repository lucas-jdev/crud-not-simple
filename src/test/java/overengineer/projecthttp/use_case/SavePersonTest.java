package overengineer.projecthttp.use_case;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import overengineer.projecthttp.domain.PersonGateway;
import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.doubletests.fakes.PersonRepositoryFakeDB;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonInsert;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonSaved;
import overengineer.projecthttp.infra.use_case.crud.person.SavePerson;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        person.setName(faker.name().nameWithMiddle());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.internet().emailAddress());
        faker.date().birthday();
        LocalDate birthDate = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
        person.setBirthDate(birthDate);
    }

    @AfterEach
    void tearDownEach() {
        person = null;
        personGateway.deleteAll();
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
