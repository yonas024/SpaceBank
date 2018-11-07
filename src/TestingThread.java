
import java.util.*;

public class TestingThread implements Runnable {

    TestingThread(String name, Wallet w, String account, int amount) {
        _name = name;
        _random = new Random();
        _wallet = w;
        _amount = amount;
        _account = account;
//        _time = _random.nextInt(100);
    }

    public void run() {
        try {
//            Thread.sleep(_time);
//            System.out.print(_name + "\n");
            _wallet.deposit(_account, _amount);
            _wallet.withdraw(_account, 5);
//            System.out.printf("%d\n", _wallet.accountBalance(_account));
        } catch (Exception e) {

        }
    }


    private String _name;
    private String _account;
    private int _time;
    private Wallet _wallet;
    private int _amount;
    private Random _random;
}
