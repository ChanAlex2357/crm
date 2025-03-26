package site.easy.to.build.crm.service.csv.validation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import site.easy.to.build.crm.exception.ImportException;

@Validated
@Service
public class ConstrainValidator<T> {
    @Autowired
    private Validator validator;

    public void validateConstraint(T object , ImportException exceptionHandler){
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if(!violations.isEmpty()){
            for (ConstraintViolation<T> violation : violations) {
                exceptionHandler.addError(violation.getMessage());
            }
        }
    }
}
