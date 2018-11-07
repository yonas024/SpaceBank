import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;

/** Represents a transaction from a withdraw or deposit.
 *  @author Yonas Kbrom
 */
public class Transaction {

    /** A transaction that contains NAME of account, TYPE of transaction, AMOUNT withdrawn/deposited,
     * and the TIMESTAMP */
    Transaction(String name, String type, int amount, String timestamp) {
        _name = name;
        _type = type;
        _amount = amount;
        _timestamp = timestamp;
    }

    /** Name of account. */
    protected String _name;
    /** Type of transaction. */
    protected String _type;
    /** Amount withdrawn/deposited. */
    protected int _amount;
    /** Time of transaction. */
    protected String _timestamp;

}