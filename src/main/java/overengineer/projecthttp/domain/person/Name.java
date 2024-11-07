package overengineer.projecthttp.domain.person;

import overengineer.projecthttp.domain.exception.BusinessException;

record Name(String value) {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 80;

    public Name {
        if (value == null || value.isBlank()) {
            throw new BusinessException("Name cannot be null or empty");
        }
        if (value.length() < MIN_NAME_LENGTH) {
            throw new BusinessException("Name must have at least 3 characters");
        }
        if (value.length() > MAX_NAME_LENGTH) {
            throw new BusinessException("Name must have a maximum of 80 characters");
        }
    }

}
