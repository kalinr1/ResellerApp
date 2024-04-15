package com.example.examprep2.validations.confirmPasswordMatcher;

import com.example.examprep2.models.dtoS.binding.UserRegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterDTO> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {

        boolean valid = userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());

        if (!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Passwords dont match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            constraintValidatorContext.buildConstraintViolationWithTemplate("Passwords don't match")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
