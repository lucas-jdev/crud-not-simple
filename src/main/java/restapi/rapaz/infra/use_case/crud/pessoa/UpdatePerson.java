package restapi.rapaz.infra.use_case.crud.pessoa;

import org.springframework.stereotype.Service;
import restapi.rapaz.domain.PersonGateway;
import restapi.rapaz.infra.exception.ApplicationException;

import java.util.UUID;

@Service
public record UpdatePerson(PersonGateway repo) {

    public PersonUpdated execute(UUID id, PersonSetUpdate dto) {
        var pessoaContainer = repo.findById(id);
        var pessoa = pessoaContainer.orElseThrow(() -> new ApplicationException("Pessoa not found"));
        if(dto.nome() != null) pessoa.setName(dto.nome());
        if(dto.sobrenome() != null) pessoa.setLastName(dto.sobrenome());
        if(dto.idade() != null) pessoa.setYear(dto.idade());
        if(dto.email() != null) pessoa.setEmail(dto.email());
        repo.save(pessoa);
        return new PersonUpdated(pessoa.getId(), pessoa.getName(), pessoa.getEmail(), pessoa.getLastName(), pessoa.getYear());
    }

}
