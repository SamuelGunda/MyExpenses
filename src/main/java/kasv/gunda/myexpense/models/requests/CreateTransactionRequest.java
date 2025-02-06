package kasv.gunda.myexpense.models.requests;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTransactionRequest {
    @NotBlank
    @Size(min = 3, max = 24, message = "Transaction name must be between 3 and 24 characters long.")
    private String transactionName;
    @Size(min = 3, max = 128, message = "Description must be between 3 and 100 characters long.")
    private String description;
    @Size(min = 3, max = 36, message = "Transaction initiator name must be between 3 and 100 characters long.")
    private String transactionInitiator;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @AssertTrue(message = "Amount must not be zero")
    public boolean isAmountNotZero() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) != 0;
    }

    private Date depositedAt;
}
