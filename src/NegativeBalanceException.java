/** Exception for reaching a negative balance.
 *  @author Yonas Kbrom
 */
public class NegativeBalanceException extends Exception {

    /** An exception that is thrown when a balance goes below 0. */
    public NegativeBalanceException(String message) {
        super(message);
    }

}
