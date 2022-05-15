package gr.aueb.cf.model;

import java.io.PrintStream;

/**
 * This program manages a Bank's <b>joint accounts</b> where account has two beneficiaries
 * Account fields:
 *      IDs of the beneficiaries
 *      IBAN of the account
 *      firstnames of the beneficiaries
 *      lastnames of the beneficiaries
 *      ssns (social security numbers) of the beneficiaries
 *      balance of the account
 * @version 1
 * @author reyke
 */

public class JointAccount {

    //private fields
    private long[] ids;
    private String iban;
    private String[] firstnames;
    private String[] lastnames;
    private String[] ssns;
    private double balance;

    //constructors
    private JointAccount() {};

    public JointAccount(long[] ids, String iban, String[] firstnames, String[] lastnames, String[] ssns, double balance) {
        this.ids = ids;
        this.iban = iban;
        this.firstnames = firstnames;
        this.lastnames = lastnames;
        this.ssns = ssns;
        this.balance = balance;
    }

    public static JointAccount getInstance() {
        return new JointAccount();
    }

    //setters & getters
    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String[] getFirstnames() {
        return firstnames;
    }

    public void setFirstnames(String[] firstnames) {
        this.firstnames = firstnames;
    }

    public String[] getLastnames() {
        return lastnames;
    }

    public void setLastnames(String[] lastnames) {
        this.lastnames = lastnames;
    }

    public String[] getSsns() {
        return ssns;
    }

    public void setSsns(String[] ssns) {
        this.ssns = ssns;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //business logic

    /**
     * This method realizes a deposit in the account
     * @param amount    the amount to be deposited in the account
     * @return          true/false if the deposit was successful or not
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            printErrorMessage(System.err, "Error: invalid amount");
            return false;
        }
    }

    /**
     * This method realizes a withdrawal in the account
     * @param amount    the amount to be withdrawn from the account
     * @param ssn       the social security number of the client trying to make the withdrawal
     * @return          true/false if the withdrawal was successful or not
     */
    public boolean withdraw(double amount, String ssn) {
        if (!isSsnValid(ssn)) {
            printErrorMessage(System.err,"Error: invalid ssn");
            return false;
        }

        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            printErrorMessage(System.err, "Error: insufficient balance");
            return false;
        }
    }

    /**
     * Converts the account state into a String
     * @return      String value
     */
    public String getJointAccountState() {
        return "id of 1st beneficiary: " + ids[0] + "\t" + "firstname of 1st beneficiary: " + firstnames[0]
                + "\t" + "lastname of 1st beneficiary: " + lastnames[0] + "\t" + "ssn of 1st beneficiary: " +
                ssns[0] + "\t" + "\n" +
                "id of 2nd beneficiary: " + ids[1] + "\t" + "firstname of 2nd beneficiary: " + firstnames[1]
                + "\t" + "last of 2nd beneficiary: " + lastnames[1] + "\t" + "ssn of 2nd beneficiary: " +
                ssns[1] + "\t" + "\n" +
                "iban: " + iban + "\t" + "balance: " + balance + "\t";
    }

    /**
     * Provides the current balance of the account
     * @return      account balance
     */
    public double getAccountBalance() {
        return getBalance();
    }

    //private client checks

    /**
     * This method checks if the ssn provided by the client matches the one we already have for this client
     * @param ssn       social security number provided by the client to check with what we already have
     * @return          true/false if provided ssn matches our records or not
     */
    private boolean isSsnValid(String ssn) {
        if (ssn == null) {
            printErrorMessage(System.err, "Error: invalid ssn");
            return false;
        }
        if (this.ssns[0] == null || this.ssns[1] == null) return false;
        if (!this.ssns[0].equals(ssn) && !this.ssns[1].equals(ssn)) return false;

        return true;
    }

    /**
     * Manages the error messages
     * @param ps            printstream to where print the respective message (eg console, file, etc)
     * @param message       message to be printed
     */
    private void printErrorMessage(PrintStream ps, String message) {
        if ((ps == null) || (message == null)) return;
        ps.println(message);
    }
}
