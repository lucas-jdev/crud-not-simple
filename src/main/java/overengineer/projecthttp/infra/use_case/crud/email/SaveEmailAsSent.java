package overengineer.projecthttp.infra.use_case.crud.email;

import org.springframework.stereotype.Service;
import overengineer.projecthttp.infra.db.email.EmailEntity;
import overengineer.projecthttp.infra.db.email.EmailRepositoryNoSQL;
import overengineer.projecthttp.infra.rabbitmq.Email;

@Service
public record SaveEmailAsSent(EmailRepositoryNoSQL emailRepositoryNoSQL) {

    public void execute(Email email) {
        EmailEntity emailEntity = new EmailEntity(email);
        emailEntity.markAsSent();
        emailRepositoryNoSQL.save(emailEntity);
    }

}
