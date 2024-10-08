package restapi.rapaz.infra.db;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.rapaz.domain.person.Person;

import java.util.UUID;

public interface PersonRepositoryORM extends JpaRepository<Person, UUID> {
}
