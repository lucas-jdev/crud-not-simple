package overengineer.projecthttp.domain.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import overengineer.projecthttp.domain.exception.BusinessException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {

    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private boolean active;

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 80;
    private static final int MIN_LAST_NAME_LENGTH = 3;
    private static final int MAX_LAST_NAME_LENGTH = 80;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 80;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public Person() {
        this.id = UUID.randomUUID();
        this.active = true;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("Name cannot be null or empty");
        }
        if (name.length() < MIN_NAME_LENGTH) {
            throw new BusinessException("Name must have at least 3 characters");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new BusinessException("Name must have a maximum of 80 characters");
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new BusinessException("Last name cannot be null or empty");
        }
        if (lastName.length() < MIN_LAST_NAME_LENGTH) {
            throw new BusinessException("Last name must have at least 3 characters");
        }
        if (lastName.length() > MAX_LAST_NAME_LENGTH) {
            throw new BusinessException("Last name must have a maximum of 80 characters");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new BusinessException("Email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new BusinessException("Invalid email format");
        }
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BusinessException("Date of birth cannot be null");
        }

        Period period = Period.between(birthDate, LocalDate.now());
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
        this.birthDate = birthDate;
    }

    public Integer getYear() {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
