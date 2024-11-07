package overengineer.projecthttp.infra.use_case.crud.email;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import overengineer.projecthttp.infra.db.email.EmailEntity;
import overengineer.projecthttp.infra.db.email.EmailRepositoryNoSQL;
import overengineer.projecthttp.infra.exception.ApplicationException;

@Service
public record SaveEmailAsReceived(EmailRepositoryNoSQL emailRepositoryNoSQL) {

    public void execute(@NonNull String id) {
        EmailEntity email = emailRepositoryNoSQL.findById(id)
                .orElseThrow(() -> new ApplicationException("Email not found"));
        email.markAsReceived();
        emailRepositoryNoSQL.save(email);
    }

}
