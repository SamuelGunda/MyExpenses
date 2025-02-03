package kasv.gunda.myexpense.services;

import kasv.gunda.myexpense.models.entities.Account;
import kasv.gunda.myexpense.models.requests.CreateAccountRequest;

public interface IAccountService {
    Account createAccount(CreateAccountRequest request);
}
