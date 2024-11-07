package overengineer.projecthttp.use_case;

import org.junit.jupiter.api.*;
import overengineer.projecthttp.infra.use_case.crud.person.FindAllPeople;

import static org.junit.jupiter.api.Assertions.*;

class FindAllPeopleTest extends AbstractPersonCommonUseCase {

    private static FindAllPeople findAllPeople;

    @BeforeAll
    static void setUp() {
        AbstractPersonCommonUseCase.init();
        findAllPeople = new FindAllPeople(personGateway);
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
    void shouldFindAllPeople() {
        var people = findAllPeople.execute();
        assertFalse(people.isEmpty());
    }

    @Nested
    class Anomalies {

        @Test
        void shouldNotFindPeople() {
            AbstractPersonCommonUseCase personCommonUseCase = new AbstractPersonCommonUseCase();
            personCommonUseCase.removeAll();
            var people = findAllPeople.execute();
            assertTrue(people.isEmpty());
        }

    }

}
