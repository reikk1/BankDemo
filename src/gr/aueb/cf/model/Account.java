package gr.aueb.cf.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * This program manages a Bank's <b>account</b> where account has one beneficiary
 * Account fields:
 *      IDs of the beneficiary
 *      IBAN of the account
 *      firstnames of the beneficiary
 *      lastnames of the beneficiary
 *      ssns (social security numbers) of the beneficiary
 *      balance of the account
 * @version 1
 * @author reyke
 */
public class Account {

    private long id;
    private String iban;
    private String lastname;
    private String firstname;
    private String ssn;
    private double balance;

    public static Account getInstance() {
        return new Account();
    }

    private Account () {
    }

    public Account(long id, String iban, String lastname, String firstname, String ssn, double balance) {
        this.id = id;
        this.iban = iban;
        this.lastname = lastname;
        this.firstname = firstname;
        this.ssn = ssn;
        this.balance = balance;
    }

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    //Business Logic

    /**
     * This method realizes a deposit in the account
     * @param amount                    the amount to be deposited in the account
     * @return                          true/false if the deposit was successful or not
     * @throws FileNotFoundException    if the log file is not found
     */
    public boolean deposit (double amount) throws FileNotFoundException {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            //check withdraw method below
            printErrorMessage(System.err, "Error: Insufficient Amount");
            printErrorMessage(new PrintStream(new FileOutputStream("log.txt", true)), "Error: Insufficient Amount");
            return false;
        }
    }

    /**
     * This method realizes a withdrawal in the account
     * @param amount                    the amount to be withdrawn from the account
     * @param ssn                       the social security number of the client trying to make the withdrawal
     * @return                          true/false if the withdrawal was successful or not
     * @throws FileNotFoundException    if the log file is not found
     */
    public boolean withdraw (double amount, String ssn) throws FileNotFoundException {
        if (!isSsnValid(ssn)) {
            printErrorMessage(System.err, "Error: Invalid ssn");
            printErrorMessage(new PrintStream(new FileOutputStream("log.txt", true)), "Error: Invalid ssn");
            return false;
        }

        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            printErrorMessage(System.err, "Error: Insufficient Balance");
            printErrorMessage(new PrintStream(new FileOutputStream("log.txt", true)), "Error: Insufficient Balance");
            return false;
        }
    }

    /**
     * Authenticates user's ssn
     * @param ssn                       The Account's ssn
     * @return                          true, if ssn equals Account's ssn and false if not
     * @throws FileNotFoundException    if the log file is not found
     */
    private boolean isSsnValid(String ssn) throws FileNotFoundException {
        if (ssn == null) {
            printErrorMessage(System.err, "Error: Invalid ssn");
            printErrorMessage(new PrintStream(new FileOutputStream("log.txt", true)), "Error: Insufficient Amount");
            return false;
        }
        if (this.ssn == null) return false;
        if (!this.ssn.equals(ssn)) return false;

        return true;
    }

    /**
     * Prints an error message.
     * @param ps        the output print stream (where the message lives)
     * @param message   the message to print each time
     */
    private void printErrorMessage(PrintStream ps, String message) {
        if ((ps == null) || (message == null)) return;
        ps.println(message);
    }

    /**
     * Gets the balance's amount of money.
     * @return  the balance.
     */
    public double getAccountBalance() {
        return getBalance(); //h boroume na poume return balance; mias k eimaste mesa stin klassi k exoume prosvasi stin private metavliti
    }

    /**
     * Gets the state of the account instance.
     * @return  the account state transformed to String.
     */
    public String convertToString() {
        return "id: " + id + "\t" + "iban: " + iban + "\t" + "lastname: " + lastname
                + "\t" + "firstname: " + firstname + "\t" + "ssn: " + ssn + "\t"
                + "balance: " + balance;
    }

    /**
     * Prints the account state
     */
    public void printAccountState() {
        System.out.println("id: " + id + "\t" + "iban: " + iban + "\t" + "lastname: " + lastname
                + "\t" + "firstname: " + firstname + "\t" + "ssn: " + ssn + "\t"
                + "balance: " + balance);
    }
}
