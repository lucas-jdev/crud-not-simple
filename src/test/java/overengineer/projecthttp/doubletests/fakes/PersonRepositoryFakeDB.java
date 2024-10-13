package overengineer.projecthttp.doubletests.fakes;

import overengineer.projecthttp.domain.PersonGateway;
import overengineer.projecthttp.domain.person.Person;

import java.util.*;

public class PersonRepositoryFakeDB implements PersonGateway {

    private final Collection<Person> people = new ArrayList<>();

    @Override
    public Person save(Person person) {
        UUID uuid = UUID.randomUUID();
        person.setId(uuid);
        people.add(person);
        return new Person(uuid, person.getName(), person.getLastName(), person.getEmail(), person.getBirthDate(), true);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return people.stream().filter(person -> Objects.equals(person.getId(), id)).findFirst();
    }

    @Override
    public Collection<Person> findAll() {
        return people;
    }

    @Override
    public Collection<Person> findAllActives() {
        return people.stream().filter(Person::isActive).toList();
    }

    @Override
    public void deleteById(UUID id) {
        people.removeIf(person -> Objects.equals(person.getId(), id));
    }

    @Override
    public void deleteAll() {
        people.clear();
    }
}
