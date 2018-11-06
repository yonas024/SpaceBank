//package SpaceBank;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;

public class Transaction {

    Transaction(String name, String type, int amount, long timestamp) {
        _name = name;
        _type = type;
        _amount = amount;
        _timestamp = timestamp;
    }

    /** Name of transaction. */
    protected String _name;
    /** Name of transaction. */
    protected String _type;
    /** Name of transaction. */
    protected int _amount;
    /** Name of transaction. */
    protected long _timestamp;

}