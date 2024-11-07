package overengineer.projecthttp.domain.person;

import overengineer.projecthttp.domain.exception.BusinessException;

record LastName(String value) {

    private static final int MIN_LAST_NAME_LENGTH = 3;
    private static final int MAX_LAST_NAME_LENGTH = 80;

    public LastName {
        if (value == null || value.isBlank()) {
            throw new BusinessException("Last name cannot be null or empty");
        }
        if (value.length() < MIN_LAST_NAME_LENGTH) {
            throw new BusinessException("Last name must have at least 3 characters");
        }
        if (value.length() > MAX_LAST_NAME_LENGTH) {
            throw new BusinessException("Last name must have a maximum of 80 characters");
        }
    }

}
