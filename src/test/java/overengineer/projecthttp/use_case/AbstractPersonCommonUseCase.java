package overengineer.projecthttp.use_case;

import com.github.javafaker.Faker;
import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.doubletests.fakes.PersonRepositoryFakeDB;

import java.time.LocalDate;

class AbstractPersonCommonUseCase {

    protected static PersonGateway personGateway;
    protected static Faker faker;
    protected Person person;

    protected static void init() {
        personGateway = new PersonRepositoryFakeDB();
        faker = new Faker();
    }

    protected void setValuesToPerson() {
        person = new Person();
        person.setName(faker.name().nameWithMiddle());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.internet().emailAddress());
        LocalDate birthDate = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
        person.setBirthDate(birthDate);
    }

    protected void removeAll() {
        personGateway.deleteAll();
    }

}
