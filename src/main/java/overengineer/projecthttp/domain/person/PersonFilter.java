package overengineer.projecthttp.domain.person;

import java.util.Map;

public interface PersonFilter<S> {

    S execute(Map<String, Object> params);

}
