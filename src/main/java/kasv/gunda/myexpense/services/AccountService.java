package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.exceptions.ConflictException;
import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.entities.Transaction;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.models.requests.TransactionRequest;
import kasv.gunda.myexpense.repositories.AccountRepository;
import kasv.gunda.myexpense.repositories.TransactionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final IUserService userService;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository, IUserService userService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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

    public Transaction transfer(String id, TransactionRequest request) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.getUserByEmail(userEmail);

        if (user == null) throw new RuntimeException("User not found");

        var account = accountRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getUsers().stream().noneMatch(u -> u.getId().equals(user.getId()))) {
            throw new AccessDeniedException("Not authorized to access this account");
        }

        var transaction = new Transaction();
        transaction.setTransactionName(request.getTransactionName());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setAccount(account);

        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }
}
