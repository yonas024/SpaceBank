//package SpaceBank;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;

public class Account {


    Account(String name) {
        _name = name;
        _balance = 0;
        _transactions = new HashMap<String, Transaction>();
        _lock = new ReentrantLock();
    }

    public int withdraw(int amount) throws Exception {
		if (_balance - amount < 0) {
//    		System.out.println("Cannot withdraw that much.");
            throw new NegativeBalanceException("Results in negative balance");
    	}
		_balance -= amount;
    	Transaction t = new Transaction(_name, "Withdraw", amount, new Date().getTime());
    	String identifier = Integer.toString(t.hashCode());
    	_transactions.put(identifier, t);
    	return _balance;
    }

    public int deposit(int amount) {
    	_balance += amount;
    	Transaction t = new Transaction(_name, "Deposit", amount, new Date().getTime());
    	String identifier = Integer.toString(t.hashCode());
    	_transactions.put(identifier,t);
    	return _balance;
    }

    public int balance() {
    	return _balance;
    }


    /** List of transactions. */
    private HashMap<String, Transaction> _transactions;
    /** Name of account. */
    private String _name;
    /** Balance of account */
    private int _balance;
    /** Lock for concurrent access */
    protected ReentrantLock _lock;
}