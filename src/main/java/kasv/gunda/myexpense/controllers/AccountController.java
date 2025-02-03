package kasv.gunda.myexpense.controllers;

import kasv.gunda.myexpense.mappers.AccountDtoMapper;
import kasv.gunda.myexpense.models.dtos.AccountDto;
import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.services.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountServices;
    private final AccountDtoMapper accountDtoMapper;

    public AccountController(IAccountService accountServices, AccountDtoMapper accountDtoMapper) {
        this.accountServices = accountServices;
        this.accountDtoMapper = accountDtoMapper;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request) {
        Account createdAccount = accountServices.createAccount(request);
        AccountDto createdAccountDto = accountDtoMapper.toDto(createdAccount);

        return ResponseEntity.ok(createdAccountDto);
    }
}
