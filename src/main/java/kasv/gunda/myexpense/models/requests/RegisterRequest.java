package kasv.gunda.myexpense.models.requests;

import lombok.*;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    private String email;
    private String fullName;
    private String password;
}
