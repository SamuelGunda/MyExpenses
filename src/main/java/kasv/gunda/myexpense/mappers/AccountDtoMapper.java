package kasv.gunda.myexpense.mappers;

import kasv.gunda.myexpense.models.dtos.AccountDto;
import kasv.gunda.myexpense.models.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoMapper {
    private final UserDtoMapper userDtoMapper;

    public AccountDtoMapper(UserDtoMapper userDtoMapper) {
        this.userDtoMapper = userDtoMapper;
    }

    public AccountDto toDto(Account account) {
        return new AccountDto(
                account.getPublicId(),
                account.getAccountName(),
                account.getBalance(),
                account.getUsers().stream().map(userDtoMapper::toDto).toList()
        );
    }
}
