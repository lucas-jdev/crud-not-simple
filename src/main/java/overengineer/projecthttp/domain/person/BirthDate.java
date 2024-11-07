package overengineer.projecthttp.domain.person;

import overengineer.projecthttp.domain.exception.BusinessException;

import java.time.LocalDate;
import java.time.Period;

record BirthDate(LocalDate value) {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 80;

    public BirthDate {
        if (value == null) {
            throw new BusinessException("Date of birth cannot be null");
        }
        Period period = Period.between(value, LocalDate.now());
        int year = period.getYears();
        if (year <= 0) {
            throw new BusinessException("Date provided is greater than or equal to today's date");
        }
        if (year > MAX_AGE) {
            throw new BusinessException("You must not be over 80 years old");
        }
        if (year < MIN_AGE) {
            throw new BusinessException("You must not be under 18 years of age");
        }
    }

    public int age() {
        return Period.between(value, LocalDate.now()).getYears();
    }
}
