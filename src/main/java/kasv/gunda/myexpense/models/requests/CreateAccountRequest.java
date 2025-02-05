package kasv.gunda.myexpense.models.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountRequest {
    @Size(max = 50)
    private String accountName;
    private double balance = 0.0;
}
