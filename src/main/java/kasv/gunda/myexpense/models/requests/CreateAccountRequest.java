package kasv.gunda.myexpense.models.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountRequest {
    @Size(min = 3, max = 50, message = "Account name must be between 3 and 50 characters long.")
    private String accountName;
    private double balance = 0.0;
}
