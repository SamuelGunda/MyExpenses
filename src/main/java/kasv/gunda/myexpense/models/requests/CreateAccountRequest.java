package kasv.gunda.myexpense.models.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @Size(min = 3, max = 36, message = "Account name must be between 3 and 36 characters long.")
    private String accountName;
    private BigDecimal balance = BigDecimal.ZERO;
}
