//package SpaceBank;

import java.util.Scanner;
import java.util.HashMap;


public class User {

    User(String name) {
        _name = name;
    }

    public void createWallet() {
    	_wallet = new Wallet();
    }

    public void createAccount(String name) throws Exception {
    	if (_wallet._accounts.keySet().contains(name)) {
    		throw new Exception("Account by that name already exists.");
    	}
    	Account a = new Account(name);
    	_wallet._accounts.put(name, a);
    }

    /** Name of user. */
    protected String _name;
    /** Wallet object. */
    protected Wallet _wallet;

}