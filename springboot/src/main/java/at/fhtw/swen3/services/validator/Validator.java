package at.fhtw.swen3.services.validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;

@Slf4j
@Service
public class Validator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final javax.validation.Validator validator = factory.getValidator();

    public <T> boolean validate(T o) {
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        violations.forEach(v -> log.error(v.getMessage()));
        if (!violations.isEmpty()) {
            log.error("Failed to validate "+o.getClass());
            return false;
            //throw new ConstraintViolationException(violations);
        }else {
            log.info(o.getClass() +" successfully validated");
            return true;
        }
    }
}
