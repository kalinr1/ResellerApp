package com.example.examprep2.validations.availableEmailCheck;


import com.example.examprep2.repositories.UserRepository;
import com.example.examprep2.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AvailableEmailChecker implements ConstraintValidator<AvailableEmailCheck, String> {

    private final UserRepository userRepository;

    public AvailableEmailChecker(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(AvailableEmailCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isEmpty();
    }

}
