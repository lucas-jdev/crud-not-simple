package overengineer.projecthttp.infra.db.email;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepositoryNoSQL extends MongoRepository<EmailEntity, String> {
}
