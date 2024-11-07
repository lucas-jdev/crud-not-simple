package overengineer.projecthttp.use_case;

import org.junit.jupiter.api.*;
import overengineer.projecthttp.infra.use_case.crud.person.FindAllPeopleActives;

import static org.junit.jupiter.api.Assertions.*;

class FindAllPeopleActivesTest extends AbstractPersonCommonUseCase {

    private static FindAllPeopleActives findAllPeopleActives;

    @BeforeAll
    static void setUp() {
        AbstractPersonCommonUseCase.init();
        findAllPeopleActives = new FindAllPeopleActives(personGateway);
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
    void shouldFindAllPeopleActives() {
        var people = findAllPeopleActives.execute();
        assertFalse(people.isEmpty());
    }

    @Nested
    class Anomalies {

        @Test
        void shouldNotFindPeopleActives() {
            AbstractPersonCommonUseCase personCommonUseCase = new AbstractPersonCommonUseCase();
            personCommonUseCase.removeAll();
            var people = findAllPeopleActives.execute();
            assertTrue(people.isEmpty());
        }

    }

}
