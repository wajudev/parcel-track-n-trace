package at.fhtw.swen3.services.validator;
import lombok.extern.slf4j.Slf4j;

import javax.validation.*;
import java.util.Set;

@Slf4j
public class Validator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final javax.validation.Validator validator = factory.getValidator();

    public <T> void validate(T o) {
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        violations.forEach(v -> log.error(v.getMessage()));
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }else {
            log.info(o.getClass() +" successfully validated");
        }
    }
}
