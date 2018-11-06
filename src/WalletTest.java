import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Created by yonas024 on 11/5/18.
 */
public class WalletTest  {

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

    @Test(expected = NegativeBalanceException.class)
    public void testIncorrectWithdraw() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.withdraw("BoA", 30);
    }

    @Test(expected = Exception.class)
    public void testAccountNameAlreadyExists() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u.createAccount("BoA");
    }

    @Test(expected = Exception.class)
    public void testUseNonexistentAccount() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("CK", 20);

    }

    @Test(expected = Exception.class)
    public void testNegativeAmount() throws Exception {
        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", -20);

    }
}