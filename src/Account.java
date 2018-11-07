import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;
import java.text.SimpleDateFormat;

/** Represents an Account within a wallet.
 *  @author Yonas Kbrom
 */
public class Account {

    /** An account identified the NAME. */
    Account(String name) {
        _name = name;
        _balance = 0;
        _transactions = new HashMap<String, Transaction>();
        _lock = new ReentrantLock();
    }

    /** Decreases balance by AMOUNT. */
    public int withdraw(int amount) throws Exception {
		if (_balance - amount < 0) {
            throw new NegativeBalanceException("Results in negative balance");
    	}
		_balance -= amount;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	Transaction t = new Transaction(_name, "Withdraw", amount, timeStamp);
    	String identifier = Integer.toString(t.hashCode());
    	_transactions.put(identifier, t);
    	return _balance;
    }

    /** Increases balance by AMOUNT. */
    public int deposit(int amount) {
    	_balance += amount;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        Transaction t = new Transaction(_name, "Deposit", amount, timeStamp);
    	String identifier = Integer.toString(t.hashCode());
    	_transactions.put(identifier,t);
    	return _balance;
    }

    /** Returns current balance of this account. */
    public int balance() {
    	return _balance;
    }

    /** Returns all of this account's transactions. */
    public HashMap<String, Transaction> transactions() {
        return _transactions;
    }


    /** List of transactions. */
    private HashMap<String, Transaction> _transactions;
    /** Name of account. */
    private String _name;
    /** Balance of account. */
    private int _balance;
    /** Lock for concurrent access to accounts. */
    protected ReentrantLock _lock;
}