package kasv.gunda.myexpense.services;

import jakarta.persistence.EntityNotFoundException;
import kasv.gunda.myexpense.events.UserDeletedEvent;
import kasv.gunda.myexpense.models.entities.User;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import kasv.gunda.myexpense.exceptions.ConflictException;
import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.entities.Transaction;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.models.requests.CreateTransactionRequest;
import kasv.gunda.myexpense.repositories.AccountRepository;
import kasv.gunda.myexpense.repositories.TransactionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
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

    public Account getAccount(String id) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return accountRepository
                .findByIdAndUsersEmail(UUID.fromString(id), userEmail)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    public Account createAccount(CreateAccountRequest request) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);

        if (user.getAccounts().stream().anyMatch(account -> account.getAccountName().equals(request.getAccountName()))) {
            throw new ConflictException("Account name");
        }

        Account account = new Account();
        account.setAccountName(request.getAccountName());
        account.setBalance(request.getBalance());
        account.getUsers().add(user);

        return accountRepository.save(account);
    }

    @Transactional
    public Transaction createTransaction(String id, CreateTransactionRequest request) {
        Account account = getAccount(id);

        Transaction transaction = new Transaction();
        transaction.setTransactionName(request.getTransactionName());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setAccount(account);

        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    @EventListener
    @Transactional
    public void handleUserDeletion(UserDeletedEvent event) {
        User user = event.getUser();
        Set<Account> accounts = user.getAccounts();

        for (Account account : accounts) {
            account.getUsers().remove(user);

            if (account.getUsers().isEmpty()) {
                accountRepository.delete(account);
            }
        }
    }
}
