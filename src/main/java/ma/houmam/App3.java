package ma.houmam;

import ma.houmam.model.AccountStatus;
import ma.houmam.model.BankAccount;
import ma.houmam.model.CurrentAccount;
import ma.houmam.utils.DataTransformationUtils;
import ma.houmam.business.BankAccountService;
import ma.houmam.business.BankAccountServiceImpl;
import ma.houmam.business.Condition;
import ma.houmam.exceptions.AccountNotFoundException;
import ma.houmam.exceptions.BalanceNotSufficientException;

import java.util.List;

public class App3 {
    public static void main(String[] args) {
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        bankAccountService.addRandomData(6);
        BankAccount bankAccount1 = new CurrentAccount(10000,"Mad",4432);
        bankAccount1.setAccountId("CC1");
        BankAccount bankAccount2 = new CurrentAccount(5000,"Mad",4432);
        bankAccount2.setAccountId("CC2");
        bankAccountService.addAccount(bankAccount2);
        bankAccountService.addAccount(bankAccount1);

       /* bankAccountService.getAllAccounts()
                .stream()
                .map(DataTransformationUtils::toJson)
                .forEach(System.out::println);*/
        BankAccount accountCC1 = null;
        BankAccount accountCC2 = null;
        try {
            accountCC1 = bankAccountService.getAccountById("CC1");
            accountCC2 = bankAccountService.getAccountById("CC2");
            //System.out.println(DataTransformationUtils.toJson(accountCC1));
            System.out.println(".....................................................");
            System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
            System.out.println(".............credit................");
            bankAccountService.credit(accountCC1.getAccountId(), 5000);
            System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
            System.out.println(".............debit.................");
            bankAccountService.debit(accountCC1.getAccountId(), 4000);
            System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
            System.out.println("...............................before transfer.........................");
            System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
            System.out.println(accountCC2.getAccountId()+" "+accountCC2.getBalance());
            System.out.println("............................after transfer............................");
            bankAccountService.transfer("CC1","CC2", 40000);
            System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
            System.out.println(accountCC2.getAccountId()+" "+accountCC2.getBalance());
            System.out.println("......................................................");

        } catch (AccountNotFoundException | BalanceNotSufficientException e) {
            System.out.println(e.getMessage());;
        }
        System.out.println("----------followed program---------");
        System.out.println(accountCC1.getAccountId()+" "+accountCC1.getBalance());
        System.out.println(accountCC2.getAccountId()+" "+accountCC2.getBalance());
        System.out.println("............CurrentAccount............");
        bankAccountService.getAllCurrentAccounts().forEach(System.out::println);
        System.out.println("............SavingAccount............");
        bankAccountService.getAllSavingAccounts().forEach(System.out::println);
        System.out.println("............TotalBalance............");
        System.out.println(bankAccountService.getTotalBalances());
        System.out.println("-------------Condition test--------------");
        List<BankAccount> bankAccounts = bankAccountService.searchBankAccount(new Condition<BankAccount>() {
            @Override
            public boolean test(BankAccount bankAccount) {
                return bankAccount.getStatus().equals(AccountStatus.ACTIVATED);
            }
        });
        bankAccounts.stream()
                .map(DataTransformationUtils::toJson)
                .forEach(System.out::println);

    }

}
