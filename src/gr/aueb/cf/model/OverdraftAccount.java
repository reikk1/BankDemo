package gr.aueb.cf.model;

import java.io.PrintStream;

/**
 * This program manages a Bank's <b>overdraft accounts</b>
 * Account fields:
 *      ID of the client
 *      IBAN of the account
 *      firstname of the client
 *      lastname of the client
 *      ssn (social security number) of the client
 *      limit up to which client can withdraw from the overdraft account
 *      balance of the account, i.e. the amount already drawn from the existing limit
 * @version 1
 * @author reyke
 */

public class OverdraftAccount {

    //private fields
    private long id;
    private String iban;
    private String firstname;
    private String lastname;
    private String ssn;
    private double balance;
    private double limit;

    //constructors
    private OverdraftAccount() {};

    public OverdraftAccount(long id, String iban, String firstname, String lastname, String ssn, double balance, double limit) {
        this.id = id;
        this.iban = iban;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.balance = balance;
        this.limit = limit;
    }

    public static OverdraftAccount getInstance() {
        return new OverdraftAccount();
    }

    //setters & getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        if (limit >= balance) {
            this.limit = limit;
        } else {
            printErrorMessage(System.err, "Error: Invalid limit");
        }
    }

    //business logic

    /**
     * This method realizes a deposit in the account
     * Noting that a deposit for the overdraft account is equivalent to repayment
     * of the existing limit
     * @param amount    the amount to be deposited in the account
     * @return          true/false if the deposit was successful or not
     */
    public boolean deposit(double amount) {
        if ((amount > 0) && (amount <= balance)) {
            balance -= amount;
            return true;
        } else {
            printErrorMessage(System.err, "Error");
            return false;
        }
    }

    /**
     * This method realizes a withdrawal in the account
     * Noting that a withdrawal for the overdraft account is equivalent to undertaking part (or all)
     * of the existing limit
     * @param amount    the amount to be withdrawn from the account
     * @param ssn       the social security number of the client trying to make the withdrawal
     * @return          true/false if the withdrawal was successful or not
     */
    public boolean withdraw(double amount, String ssn) {
        if (!isSsnValid(ssn)) {
            printErrorMessage(System.err,"Error: invalid ssn");
            return false;
        }

        if ((amount > 0) && (hasAvailability(limit, balance, amount))) {
            balance += amount;
            return true;
        } else {
            printErrorMessage(System.err, "Error");
            return false;
        }
    }

    /**
     * Converts the account state into a String
     * @return      String value
     */
    public String getOverdraftAccountState() {
        return "id: " + id + "\t" + "iban: " + iban + "\t" + "firstname: " + firstname
                + "\t" + "lastname: " + lastname + "\t" + "ssn: " + ssn + "\t" +
                "limit: " + limit + "\t" + "balance: " + balance + "\t" + "availability: "
                + (limit - balance);
    }

    /**
     * Provides the current limit of the account
     * @return      account limit up to which client can withdraw at any given time
     */
    public double getAccountLimit() {
        return getLimit();
    }

    /**
     * Provides the current balance of the account
     * @return      account balance, i.e. part of limit that has been withdrawn up to date
     */
    public double getAccountBalance() {
        return getBalance();
    }

    /**
     * Provides the current available limit of the account
     * @return      account available limit that the client can withdraw until full utilization is achieved
     */
    public double getAccountAvailability() {
        return getAccountLimit() - getAccountBalance();
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
        if (this.ssn == null) return false;
        if (!this.ssn.equals(ssn)) return false;

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

    /**
     * This method checks if client can still make withdrawals from the existing limit
     * @param limit         account's existing limit
     * @param balance       account's existing balance
     * @param amount        amount that is requested to be withdrawn
     * @return              true/false if there is available headroom in the existing limit for further withdrawals or not
     */
    private boolean hasAvailability(double limit, double balance, double amount) {
        double availableBalance;

        availableBalance = limit - balance;

        if (availableBalance >= amount) {
            return true;
        } else {
            return false;
        }
    }
}
