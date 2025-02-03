package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.exceptions.ConflictException;
import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.repositories.AccountRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final IUserService userService;

    public AccountService(AccountRepository accountRepository, IUserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }


    public Account createAccount(CreateAccountRequest request) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.getUserByEmail(userEmail);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getAccounts().stream().anyMatch(account -> account.getAccountName().equals(request.getAccountName()))) {
            throw new ConflictException("Account name");
        }

        var account = new Account();
        account.setAccountName(request.getAccountName());
        account.setBalance(request.getBalance());
        account.getUsers().add(user);

        return accountRepository.save(account);
    }
}
