import static org.junit.Assert.*;
import org.junit.Test;

/** Test cases that test the overall structure of the bank.
 *  @author Yonas Kbrom
 */
public class WalletTest  {

    /** Tests the deposit method. */
    @Test
    public void testDeposit() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        assertEquals(60, u._wallet.accountBalance("BoA"));
    }

    /** Tests the withdraw method. */
    @Test
    public void testWithdraw() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.withdraw("BoA", 5);
        assertEquals(55, u._wallet.accountBalance("BoA"));
    }

    /** Tests the transfer method. */
    @Test
    public void testTransfer() throws Exception{
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.withdraw("BoA", 5);
        u.createAccount("CK");
        u._wallet.transfer("BoA", "CK", 10);
        assertEquals(45, u._wallet.accountBalance("BoA"));
        assertEquals(10, u._wallet.accountBalance("CK"));
    }

    /** Tests concurrent access by two different threads. */
    @Test
    public void testConcurrency() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        Thread t1 = new Thread(new TestingThread("hello", u._wallet, "BoA", 20));
        Thread t2 = new Thread(new TestingThread("bye", u._wallet, "BoA", 30));
        t1.start();
        t2.start();
        Thread.sleep(100);
        assertEquals(40, u._wallet.accountBalance("BoA"));
    }

    /** Tests the list of transactions. */
    @Test
    public void testTransactions() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 10);
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 30);
        u._wallet.withdraw("BoA", 5);
        assertEquals(4, u._wallet.accountTransactions("BoA").size());
    }

    /** Tests withdraw too much from account. */
    @Test(expected = NegativeBalanceException.class)
    public void testIncorrectWithdraw() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.withdraw("BoA", 30);
    }

    /** Tests creating account with already existing name. */
    @Test(expected = Exception.class)
    public void testAccountNameAlreadyExists() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u.createAccount("BoA");
    }

    /** Tests use of account that does not exist. */
    @Test(expected = Exception.class)
    public void testUseNonexistentAccount() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("CK", 20);

    }

    /** Tests depositing negative amount. */
    @Test(expected = Exception.class)
    public void testDepositNegativeAmount() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", -20);
    }

    /** Tests withdrawing negative amount. */
    @Test(expected = Exception.class)
    public void testWithdrawNegativeAmount() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", -20);
    }


}