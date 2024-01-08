package ma.houmam;

import ma.houmam.model.BankAccount;
import ma.houmam.model.CurrentAccount;
import ma.houmam.model.SavingAccount;

public class Main {
    public static void main(String[] args) {

        String input = "boo";
        String input1 = "boo";
        System.out.println(input1 == input);

        System.out.println("--------------------------------");
        BankAccount bankAccount = new CurrentAccount(10000,"MAD", 6789);
        BankAccount bankAccount1 = new SavingAccount(10000,"MAD", 0.5);
        BankAccount bankAccount2 = bankAccount;
        //o = o1 => o.hashcode() = o1.hashcode() get in consideration memory addresses and object state
        System.out.println("*************************************");
        bankAccount1.setAccountId(bankAccount.getAccountId());
        System.out.println(bankAccount);
        System.out.println(bankAccount1);
        System.out.println("*************************************");
        System.out.println("HAshCode  = "+ bankAccount.hashCode());
        System.out.println("HAshCode  = "+ bankAccount1.hashCode());
        System.out.println("equals methode : "+bankAccount.equals(bankAccount1));
        System.out.println("== sign method :"+(bankAccount == bankAccount2));
        System.out.println("*************************************");

    }
    public static void printAccount(BankAccount bankAccount){
        System.out.println("+++++++++++++++++++++");
        System.out.println(bankAccount);
        System.out.println("+++++++++++++++++++++");


    }
}