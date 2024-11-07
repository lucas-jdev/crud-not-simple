package overengineer.projecthttp.domain.person;

import overengineer.projecthttp.domain.exception.BusinessException;

import java.util.regex.Pattern;

record Email(String value) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public Email {
        if (value == null || value.isBlank()) {
            throw new BusinessException("Email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new BusinessException("Invalid email format");
        }
    }

}
