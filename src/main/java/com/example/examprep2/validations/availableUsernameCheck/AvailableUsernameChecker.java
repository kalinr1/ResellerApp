package com.example.examprep2.validations.availableUsernameCheck;

import com.example.examprep2.repositories.UserRepository;
import com.example.examprep2.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AvailableUsernameChecker implements ConstraintValidator<AvailableUsernameCheck, String> {

    private final UserRepository userRepository;

    public AvailableUsernameChecker(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(AvailableUsernameCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(username).isEmpty();
    }

}
