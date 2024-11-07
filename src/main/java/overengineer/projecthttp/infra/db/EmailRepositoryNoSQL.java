package overengineer.projecthttp.infra.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepositoryNoSQL extends MongoRepository<EmailEntity, String> {
}
