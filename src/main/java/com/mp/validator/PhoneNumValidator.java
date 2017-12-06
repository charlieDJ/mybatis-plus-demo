package com.mp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumValidator implements ConstraintValidator<PhoneNum, String> {

    @Override
    public void initialize(PhoneNum constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.matches("((\\+86)|(86))?1[3|4|5|8]\\d{9}")){
            return true;
        }
        return false;
    }
}
