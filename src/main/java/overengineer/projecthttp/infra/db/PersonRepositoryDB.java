package overengineer.projecthttp.infra.db;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import overengineer.projecthttp.domain.person.PersonFilter;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.domain.person.Person;
import overengineer.projecthttp.infra.mapper.PersonMapper;

import java.util.*;

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
                .map(PersonMapper::entityToDomain);
    }

    @Override
    public Collection<Person> findAll() {
        return repository.findAll()
                .stream()
                .map(PersonMapper::entityToDomain)
                .toList();
    }

    @Override
    public Collection<Person> findAll(PersonFilter<?> filter, Map<String, Object> args) {
        Specification<PersonEntity> specification = (Specification<PersonEntity>) filter.execute(args);
        return repository.findAll(specification)
                .stream()
                .map(PersonMapper::entityToDomain)
                .toList();
    }

    @Override
    public Collection<Person> findAllActives() {
        return repository.findAllByActive()
                .stream()
                .map(PersonMapper::entityToDomain)
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
