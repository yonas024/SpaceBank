/** Represents a User for the bank.
 *  @author Yonas Kbrom
 */
public class User {

    /** A user identified by NAME. */
    User(String name) {
        _name = name;
    }

    /** Creates a wallet for this user. */
    public void createWallet() {
    	_wallet = new Wallet();
    }

    /** Creates an account within this users wallet with NAME. */
    public void createAccount(String name) throws Exception {
    	if (_wallet._accounts.keySet().contains(name)) {
    		throw new Exception("Account by that name already exists.");
    	}
    	Account a = new Account(name);
    	_wallet._accounts.put(name, a);
    }

    /** Name of the user. */
    protected String _name;
    /** Wallet of the user. */
    protected Wallet _wallet;

}