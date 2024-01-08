package ma.houmam.business;

import ma.houmam.exceptions.BalanceNotSufficientException;
import ma.houmam.model.BankAccount;
import ma.houmam.exceptions.AccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    BankAccount addAccount(BankAccount bankAccount);
    List<BankAccount> getAllAccounts();
    BankAccount getAccountById(String accountId) throws AccountNotFoundException;
    void addRandomData(int size);
    void debit(String accountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount) throws AccountNotFoundException;
    void transfer(String sourceAccountId, String destinationAccountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
    List<BankAccount> getAllCurrentAccounts();
    List<BankAccount> getAllSavingAccounts();
    double getTotalBalances();
    List<BankAccount> searchBankAccount(Condition<BankAccount> condition);

}
