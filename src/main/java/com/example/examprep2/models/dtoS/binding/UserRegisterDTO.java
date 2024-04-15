package com.example.examprep2.models.dtoS.binding;

import com.example.examprep2.validations.availableEmailCheck.AvailableEmailCheck;
import com.example.examprep2.validations.availableUsernameCheck.AvailableUsernameCheck;
import com.example.examprep2.validations.confirmPasswordMatcher.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatch
public class UserRegisterDTO {

    @Size (min = 3, max = 20)
    @NotNull
    @AvailableUsernameCheck
    private String username;

    @Email
    @NotBlank
    @NotNull
    @AvailableEmailCheck
    private String email;

    @Size (min = 3, max = 20)
    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @Size (min = 3, max = 20)
    @NotBlank
    private String confirmPassword;
}
