package restapi.rapaz.infra.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import restapi.rapaz.domain.PersonGateway;
import restapi.rapaz.domain.person.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryDB implements PersonGateway {

    private final PersonRepositoryORM repository;

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
