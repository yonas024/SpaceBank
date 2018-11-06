//package SpaceBank;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class Wallet {

    Wallet() {
        _accounts = new HashMap<String, Account>();
    }

    public void withdraw(String account, int amount) throws Exception {
    	errors(account, amount);
    	Account a = _accounts.get(account);
    	a._lock.lock();
    	try {
    		a.withdraw(amount);
    	} finally {
    		a._lock.unlock();
    	}
    	String message = "Successfully withdrew $" + Integer.toString(amount) + ". New balance $" + Integer.toString(a.balance()) + ".";
    	System.out.println(message);
    }

    public void deposit(String account, int amount) throws Exception {
    	errors(account, amount);
    	Account a = _accounts.get(account);
    	a._lock.lock();
    	a.deposit(amount);
    	String message = "Successfully deposited $" + Integer.toString(amount) + ". New balance $" + Integer.toString(a.balance()) + ".";
    	System.out.println(message);
    	a._lock.unlock();

    }

    public int accountBalance(String account) {
    	return _accounts.get(account).balance();
    }

    public void transfer(String from, String to, int amount) throws Exception {
    	errors(from, amount);
        errors(to, amount);
    	Account f = _accounts.get(from);
    	Account t = _accounts.get(to);
    	f.withdraw(amount);
    	t.deposit(amount);
    	String message = "Transfer Successful.";
    	System.out.println(message);
    }

    public void errors(String account, int amount) throws Exception{
    	if (_accounts.get(account) == null) {
            throw new Exception("No account by the name" + account);
    	}
    	if (amount <= 0) {
    		throw new Exception("Amount must be greater than 0");
    	}
    }


    /** List of accounts*/
    protected HashMap<String, Account> _accounts;

}