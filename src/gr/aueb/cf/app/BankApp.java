package gr.aueb.cf.app;

import gr.aueb.cf.model.OverdraftAccount;
import gr.aueb.cf.model.JointAccount;
import gr.aueb.cf.model.Account;
import java.io.FileNotFoundException;

public class BankApp {
    public static void main(String[] args) throws FileNotFoundException {

        Account luke = new Account(12, "GA111", "Skywalker", "Luke", "AB123", 1000);
        Account vader = Account.getInstance();

        vader.setId(34);
        vader.setIban("DS222");
        vader.setLastname("Darth");
        vader.setFirstname("Vader");
        vader.setSsn("BC456");
        vader.setBalance(1500);

        OverdraftAccount chewbacca = new OverdraftAccount(56, "GA333", "Chewbacca", "Unknown", "CD789", 0, 5000);
        OverdraftAccount r2D2 = OverdraftAccount.getInstance();

        r2D2.setId(78);
        r2D2.setIban("GA444");
        r2D2.setFirstname("R2");
        r2D2.setLastname("D2");
        r2D2.setSsn("DE101112");
        r2D2.setBalance(3000);
        r2D2.setLimit(10000);

        JointAccount soloFamily = new JointAccount(new long[] {89, 90},"GA555", new String[] {"Hans", "Leia"},new String[] {"Solo", "Organa Solo"}, new String[] {"EF1", "EF2"}, 2000);

        if (luke.deposit(3006))  {
            System.out.println("Deposit was successful");
        } else {
            System.out.println("Something went wrong with your deposit");
        }

        System.out.println(luke.convertToString());

        if (vader.withdraw(1500, "BC456")) {
            System.out.println("withdrawal was successful");
        } else {
            System.out.println("Something went wrong with your withdrawal");
        }

        System.out.println(vader.convertToString());

        if (r2D2.withdraw(2300, "DE101112")) {
            System.out.println("withdrawal was successful");
        } else {
            System.out.println("Something went wrong with your withdrawal");
        }

        System.out.println(r2D2.getOverdraftAccountState());

        if (r2D2.deposit(3500)) {
            System.out.println("Deposit was successful");
        } else {
            System.out.println("Something went wrong with your deposit");
        }

        System.out.println(r2D2.getOverdraftAccountState());

        if (chewbacca.withdraw(1450, "CD789")) {
            System.out.println("withdrawal was successful");
        } else {
            System.out.println("Something went wrong with your withdrawal");
        }

        System.out.println(chewbacca.getOverdraftAccountState());

        if (chewbacca.deposit(1000)) {
            System.out.println("Deposit was successful");
        } else {
            System.out.println("Something went wrong with your deposit");
        }

        System.out.println(chewbacca.getOverdraftAccountState());

        if (soloFamily.deposit(2100)) {
            System.out.println("Deposit was successful");
        } else {
            System.out.println("Something went wrong with your deposit");
        }

        System.out.println(soloFamily.getJointAccountState());

        if (soloFamily.withdraw(2400, "EF2")) {
            System.out.println("withdrawal was successful");
        } else {
            System.out.println("Something went wrong with your withdrawal");
        }

        System.out.println(soloFamily.getJointAccountState());
    }
}
