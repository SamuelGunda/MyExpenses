package kasv.gunda.myexpense.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private long expiresIn;
}
