package kasv.gunda.myexpense.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    @Email(message = "Please provide a valid email address.")
    private String email;

    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters long.")
    private String fullName;
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message =
                    "Password must be at least 8 characters long and include a digit, " +
                    "a lowercase letter, an uppercase letter, and a special character.")
    private String password;
}
