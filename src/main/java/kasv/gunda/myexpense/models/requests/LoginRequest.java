package kasv.gunda.myexpense.models.requests;

import lombok.*;

@AllArgsConstructor
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
