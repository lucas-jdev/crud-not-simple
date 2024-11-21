package overengineer.projecthttp.infra.db.filter.person;

import overengineer.projecthttp.infra.db.PersonEntity;
import overengineer.projecthttp.infra.db.filter.Filter;
import overengineer.projecthttp.infra.exception.ApplicationException;

import java.util.Collection;

public final class PersonFilterFactory {

    private static final String KEY_AGE_START = "ageStart";
    private static final String KEY_AGE_END = "ageEnd";

    private PersonFilterFactory() {
        throw new ApplicationException("FilterFactory should not be instantiated");
    }

    public static Filter<PersonEntity> getInstanceOfFilterSpecification(Collection<String> keys) {
        if (keys.contains(KEY_AGE_START) && keys.contains(KEY_AGE_END)) {
            return new PersonFilterByAgeRangeDB();
        }
        throw new ApplicationException("Invalid parameters");
    }

}
