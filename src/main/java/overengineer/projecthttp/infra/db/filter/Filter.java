package overengineer.projecthttp.infra.db.filter;

import org.springframework.data.jpa.domain.Specification;

public interface Filter<T> {

    Specification<T> execute(Object... args);

}
