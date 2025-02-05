package kasv.gunda.myexpense.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AccountDto {
    private UUID publicId;
    private String accountName;
    private BigDecimal balance;
    private List<UserDto> users;
}
