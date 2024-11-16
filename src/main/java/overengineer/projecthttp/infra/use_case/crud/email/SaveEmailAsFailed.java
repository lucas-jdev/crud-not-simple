package overengineer.projecthttp.infra.use_case.crud.email;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.infra.db.email.EmailRepositoryNoSQL;

@Service
public record SaveEmailAsFailed(EmailRepositoryNoSQL emailRepositoryNoSQL) {

    public void execute(String id) {
        emailRepositoryNoSQL.findById(id).ifPresent(email -> {
            email.markAsFailed();
            emailRepositoryNoSQL.save(email);
        });
    }

}
