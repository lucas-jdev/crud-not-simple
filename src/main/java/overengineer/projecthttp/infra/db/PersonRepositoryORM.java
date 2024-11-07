package overengineer.projecthttp.infra.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryORM extends JpaRepository<PersonEntity, UUID> {

    @Query("SELECT p FROM PersonEntity p WHERE p.active")
    Collection<PersonEntity> findAllByActive();

    @Query("SELECT p FROM PersonEntity p WHERE p.publicId = :id")
    Optional<PersonEntity> findByPublicId(UUID id);

    @Modifying
    @Query("UPDATE PersonEntity SET active = false WHERE publicId = :id")
    void deleteById(@NonNull UUID id);

    @Modifying
    @Query("UPDATE PersonEntity SET active = false")
    void deleteAll();

}
