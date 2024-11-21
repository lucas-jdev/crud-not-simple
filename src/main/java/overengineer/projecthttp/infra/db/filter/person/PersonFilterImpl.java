package overengineer.projecthttp.infra.db.filter.person;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import overengineer.projecthttp.domain.person.PersonFilter;
import overengineer.projecthttp.infra.db.PersonEntity;
import overengineer.projecthttp.infra.db.filter.Filter;

import java.util.Map;

@Component
public class PersonFilterImpl implements PersonFilter<Specification<PersonEntity>> {

    @Override
    public Specification<PersonEntity> execute(Map<String, Object> params) {
        Filter<PersonEntity> filter = PersonFilterFactory.getInstanceOfFilterSpecification(params.keySet());
        return filter.execute(params.values().toArray());
    }
    
}
