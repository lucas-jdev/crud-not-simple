package overengineer.projecthttp.infra.use_case.crud.person;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import overengineer.projecthttp.domain.person.PersonFilter;
import overengineer.projecthttp.domain.person.PersonGateway;
import overengineer.projecthttp.infra.mapper.PersonMapper;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;

import java.util.Collection;
import java.util.Map;

@Service
public record FindAllPeople(PersonGateway repo){

    public Collection<PersonFound> execute() {
        return repo.findAll()
            .stream()
            .map(PersonMapper::domainToOutputFound)
            .toList();
    }

    public Collection<PersonFound> execute(@NonNull PersonFilter<?> filter, @NonNull Map<String, Object> params){
        return repo.findAll(filter, params)
                .stream()
                .map(PersonMapper::domainToOutputFound)
                .toList();
    }

}
