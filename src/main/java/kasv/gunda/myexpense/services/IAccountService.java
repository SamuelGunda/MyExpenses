package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.entities.Transaction;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;
import kasv.gunda.myexpense.models.requests.TransactionRequest;

public interface IAccountService {
    Account getAccount(String id);
    Account createAccount(CreateAccountRequest request);
    Transaction transfer(String id, TransactionRequest request);
}
