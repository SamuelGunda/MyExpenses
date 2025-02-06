package kasv.gunda.myexpense.controllers;

import jakarta.validation.Valid;
import kasv.gunda.myexpense.mappers.AccountDtoMapper;
import kasv.gunda.myexpense.models.dtos.AccountDto;
import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.entities.Transaction;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.models.requests.TransactionRequest;
import kasv.gunda.myexpense.services.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IAccountService accountServices;
    private final AccountDtoMapper accountDtoMapper;

    public AccountController(IAccountService accountServices, AccountDtoMapper accountDtoMapper) {
        this.accountServices = accountServices;
        this.accountDtoMapper = accountDtoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        Account account = accountServices.getAccount(id);
        AccountDto accountDto = accountDtoMapper.toDto(account);

        return ResponseEntity.ok(accountDto);
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account createdAccount = accountServices.createAccount(request);
        AccountDto createdAccountDto = accountDtoMapper.toDto(createdAccount);

        return ResponseEntity.ok(createdAccountDto);
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<Transaction> transfer(@PathVariable String id, @Valid @RequestBody TransactionRequest request) {
        Transaction transaction = accountServices.createTransaction(id, request);

        return ResponseEntity.ok(transaction);
    }

}
