package ma.houmam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.houmam.model.BankAccount;
import ma.houmam.model.CurrentAccount;
import ma.houmam.model.SavingAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws JsonProcessingException {
        BankAccount[] bankAccounts = new BankAccount[4];
        bankAccounts[0] = new CurrentAccount(23462,"Mad",86869);
        bankAccounts[1] = new SavingAccount(12346,"usd",5.6);
        bankAccounts[2] = new SavingAccount(24346,"eur",4.3);
        bankAccounts[3] = new CurrentAccount(23646,"usd",403038);
        System.out.println("###########array###############");
        for (BankAccount b :bankAccounts) {
            /*
            if (b instanceof CurrentAccount)
                System.out.println(((CurrentAccount)b).getType());
            else if (b instanceof SavingAccount)
                System.out.println(((SavingAccount)b).getType());
             */
            b.print();
            System.out.println(b.getType());
            if (b instanceof CurrentAccount)
                System.out.println("overDraft = "+ ((CurrentAccount)b).getOverDraft());
            else if (b instanceof SavingAccount) {
                System.out.println("Rate = "+((SavingAccount)b).getInterestRate());
            }
        }
        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(new SavingAccount(333,"foo",7.8));
        bankAccountList.add(new CurrentAccount(444,"moo",3456));
        bankAccountList.add(new CurrentAccount(222,"boo",8584));
        bankAccountList.add(new SavingAccount(777,"bazz",4.5));
        bankAccountList.add(new CurrentAccount(666,"fizz",9875));

        System.out.println("++++++++++++++lists+++++++++++++++++++");

        for (BankAccount b : bankAccountList){
            System.out.println(b);
        }
        System.out.println("_________Map____________");
        Map<String, BankAccount> bankAccountMap = new HashMap<>();
        bankAccountMap.put("cc1", new CurrentAccount(222333,"fizzbazz",5555));
        bankAccountMap.put("cc2", new SavingAccount(4972, "fizz",6.7));
        bankAccountMap.put("cc3", new CurrentAccount(322424, "bazz",7777));
        System.out.println("+++++++++++++loop through keys+++++++++++");
        for (String key:bankAccountMap.keySet()) {
            System.out.println(bankAccountMap.get(key));
        }
        System.out.println("###########loop by values with json format##############");
        for (BankAccount b:bankAccountMap.values()) {
            System.out.println(toJson(b));
        }
        System.out.println("...............looping by both keys and values..............");
        for (Map.Entry<String, BankAccount> entry : bankAccountMap.entrySet()) {
            System.out.println("the key = "+entry.getKey()+" && the value "+entry.getValue());
        }
    }
    public static String toJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
