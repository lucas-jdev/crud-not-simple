package overengineer.projecthttp.domain.person;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PersonGateway {

    /**
     * This method is responsible for saving a person.
     * PS: There is a side effect on the person parameter
     *
     * @param person
     * @return new instance of person
     */
    Person save(Person person);

    /**
     * This method is responsible for finding a person by id.
     *
     * @param id type UUID
     * @return Optional<Person>
     */
    Optional<Person> findById(UUID id);

    /**
     * This method is responsible for find all people.
     * @return Collection<Person>
     */
    Collection<Person> findAll();

    /**
     * This method is responsible for find all people by filter
     * @param filter PersonFilter
     * @param args Map with key and values
     * @return
     */
    Collection<Person> findAll(PersonFilter<?> filter, Map<String, Object> args);

    /**
     * This method is responsible for find all people actives.
     *
     * @return Collection<Person>
     */
    Collection<Person> findAllActives();

    /**
     * This method is responsible for deleting a person by id.
     *
     * @param id type UUID
     */
    void deleteById(UUID id);

    /**
     * This method is responsible for deleting all people.
     */
    void deleteAll();
}
