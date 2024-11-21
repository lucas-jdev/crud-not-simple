package overengineer.projecthttp.infra.db.filter.person;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import overengineer.projecthttp.infra.db.PersonEntity;
import overengineer.projecthttp.infra.db.filter.Filter;
import overengineer.projecthttp.infra.exception.ApplicationException;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

final class PersonFilterByAgeRangeDB implements Filter<PersonEntity> {

    private static final LocalDate TODAY = LocalDate.now();

    @Override
    public Specification<PersonEntity> execute(@NonNull Object... args) {
        if (args.length != 2)
            throw new ApplicationException("filterByAgeRangeDB requires two parameters");

        final Integer ageStart = convertArgToInt(args[0]);
        final Integer ageEnd = convertArgToInt(args[1]);

        final LocalDate startDate = Year.now().minusYears(ageEnd).atMonth(TODAY.getMonthValue()).atDay(TODAY.getDayOfMonth());
        final LocalDate endDate = Year.now().minusYears(ageStart).atMonth(TODAY.getMonthValue()).atDay(TODAY.getDayOfMonth());

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("birthDate"), startDate, endDate);
    }

    private Integer convertArgToInt(Object arg) {
        try{
            return Optional.ofNullable(arg)
                    .map(Object::toString)
                    .map(Integer::parseInt)
                    .orElseThrow(() -> new ApplicationException("parameter is null"));
        } catch (NumberFormatException e) {
            throw new ApplicationException("parameter is not a number");
        }

    }

}
