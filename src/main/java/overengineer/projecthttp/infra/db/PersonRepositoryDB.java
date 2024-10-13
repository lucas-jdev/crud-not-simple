package overengineer.projecthttp.infra.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import overengineer.projecthttp.domain.PersonGateway;
import overengineer.projecthttp.domain.person.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryDB implements PersonGateway {

    private final PersonRepositoryORM repository;

    @Override
    public Person save(Person person) {
        Optional<PersonEntity> entityFound = repository.findByPublicId(person.getId());
        entityFound.ifPresentOrElse(entity -> {
            entity.setName(person.getName());
            entity.setLastName(person.getLastName());
            entity.setEmail(person.getEmail());
            entity.setBirthDate(person.getBirthDate());
            entity.setActive(person.isActive());
            repository.save(entity);
        }, () -> {
            PersonEntity entity = new PersonEntity(null, person.getId(), person.getName(), person.getLastName(), person.getEmail(), person.getBirthDate(), person.isActive());
            repository.save(entity);
        });
        return person;
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return repository.findByPublicId(id)
                .map(entity -> new Person(entity.getPublicId(), entity.getName(),
                        entity.getLastName(), entity.getEmail(),
                        entity.getBirthDate(), entity.isActive()));
    }

    @Override
    public Collection<Person> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> new Person(entity.getPublicId(), entity.getName(),
                    entity.getLastName(), entity.getEmail(),
                    entity.getBirthDate(), entity.isActive()))
                .toList();
    }

    @Override
    public Collection<Person> findAllActives() {
        return repository.findAllByActive()
                .stream()
                .map(entity -> new Person(entity.getPublicId(), entity.getName(),
                        entity.getLastName(), entity.getEmail(),
                        entity.getBirthDate(), entity.isActive()))
                .toList();
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
