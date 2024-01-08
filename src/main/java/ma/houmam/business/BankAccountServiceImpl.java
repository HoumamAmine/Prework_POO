package ma.houmam.business;

import ma.houmam.exceptions.AccountNotFoundException;
import ma.houmam.exceptions.BalanceNotSufficientException;
import ma.houmam.model.AccountStatus;
import ma.houmam.model.BankAccount;
import ma.houmam.model.CurrentAccount;
import ma.houmam.model.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankAccountServiceImpl implements BankAccountService {
    List<BankAccount> bankAccountList = new ArrayList<>();
    @Override
    public BankAccount addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountList;
    }

    @Override
    public BankAccount getAccountById(String accountId) throws AccountNotFoundException {
        //approach declarative
        return bankAccountList
                .stream()
                .filter(b -> b.getAccountId().equals(accountId))
                .findFirst().orElseThrow(() -> new AccountNotFoundException(String.format("Account %s not found", accountId)));


        /*//approach imperative
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getAccountId().equals(accountId))
                return bankAccount;
        }
        throw new AccountNotFoundException(String.format("Account %s not found", accountId));*/
    }

    @Override
    public void addRandomData(int size) {
        BankAccount bankAccount;
        AccountStatus[] values = AccountStatus.values();
        Random random = new Random();
        for (int i = 0; i < size; i++){
            if (Math.random() < 0.5){
                bankAccount = new CurrentAccount(
                        Math.random() * 1000000,
                        Math.random() < 0.5 ? "Mad" : "Usd",
                        Math.random() * 50000);
            }
            else {
                bankAccount = new SavingAccount(
                        Math.random() * 1000000,
                        Math.random() < 0.5 ? "Mad" : "Usd",
                        3 + Math.random() * 7);
            }
            bankAccount.setStatus(values[random.nextInt(values.length)]);
            bankAccountList.add(bankAccount);
        }
    }

    @Override
    public void credit(String accountId, double amount) throws AccountNotFoundException {
        BankAccount bankAccount = getAccountById(accountId);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

    @Override
    public void debit(String accountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = getAccountById(accountId);
        if (amount > bankAccount.getBalance())
            throw new BalanceNotSufficientException("Balance not sufficient");
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    @Override
    public void transfer(String sourceAccountId, String destinationAccountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        if (getAccountById(sourceAccountId).getBalance() < amount) {
            throw new BalanceNotSufficientException("Balance not sufficient");
        }
        credit(destinationAccountId, amount);
        debit(sourceAccountId, amount);
    }

    @Override
    public List<BankAccount> getAllCurrentAccounts() {
        return bankAccountList
                .stream()
                .filter(b -> b.getType().equals("CurrentAccount"))
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAccount> getAllSavingAccounts() {
        return bankAccountList
                .stream()
                .filter(new Predicate<BankAccount>() {
                    @Override
                    public boolean test(BankAccount bankAccount) {
                        return bankAccount.getType().equals("SavingAccount");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalBalances() {
        return bankAccountList
                .stream()
                .map(b -> b.getBalance())
                .reduce(0.0, (a, v) -> a + v);
    }

    @Override
    public List<BankAccount> searchBankAccount(Condition condition) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (BankAccount bankAccount: bankAccountList){
            if (condition.test(bankAccount))
                bankAccounts.add(bankAccount);
        }
        return bankAccounts;
    }
}
