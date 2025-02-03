package kasv.gunda.myexpense.models.requests;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String accountName;
    private double balance = 0.0;
}
