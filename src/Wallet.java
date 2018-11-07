import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/** Represents a wallet for a specific User in the bank.
 *  @author Yonas Kbrom
 */
public class Wallet {

    /** The wallet of a single user. */
    Wallet() {
        _accounts = new HashMap<String, Account>();
    }

    /** Used for decreasing the balance of ACCOUNT by AMOUNT. */
    public void withdraw(String account, int amount) throws Exception {
    	errors(account, amount);
    	Account a = _accounts.get(account);
    	a._lock.lock();
    	try {
    		a.withdraw(amount);
    	} finally {
    		a._lock.unlock();
    	}
    }

    /** Used for increasing the balance of ACCOUNT by AMOUNT. */
    public void deposit(String account, int amount) throws Exception {
    	errors(account, amount);
    	Account a = _accounts.get(account);
    	a._lock.lock();
    	a.deposit(amount);
    	a._lock.unlock();

    }

    /** Returns the balance of AMOUNT. */
    public int accountBalance(String account) {
    	return _accounts.get(account).balance();
    }

    /** Decreases the balance of FROM by amount, increase the balance of TO by AMOUNT. */
    public void transfer(String from, String to, int amount) throws Exception {
    	errors(from, amount);
        errors(to, amount);
    	Account f = _accounts.get(from);
    	Account t = _accounts.get(to);
        f._lock.lock();
        t._lock.lock();
    	f.withdraw(amount);
    	t.deposit(amount);
        t._lock.unlock();
        f._lock.unlock();
    }

    /** Returns the last N transactions of ACCOUNT, returns all if no number specified. */
    public HashMap<String, Transaction> accountTransactions(String account, int... n) {

        Account a = _accounts.get(account);
        int s = n.length > 0 ? n[0] : a.transactions().size();
        return a.transactions();
    }

    /** Helper method to identify errors with ACCOUNT and/or AMOUNT. */
    public void errors(String account, int amount) throws Exception{
    	if (_accounts.get(account) == null) {
            throw new Exception("No account by the name " + account);
    	}
    	if (amount <= 0) {
    		throw new Exception("Amount must be greater than 0");
    	}
    }


    /** List of accounts within the wallet. */
    protected HashMap<String, Account> _accounts;

}