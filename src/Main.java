//package SpaceBank;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Date;

public class Main {

    public static void main(String[] unused) throws Exception {
        
        // HashMap<String, Table> db = new HashMap<>();

        // Scanner input = new Scanner(System.in);
        // CommandInterpreter interpreter =
        //     new CommandInterpreter(db, input, System.out);

        // while (true) {
        //     try {
        //         if (!interpreter.statement()){
        //             break;
        //         }
        //     } catch (DBException e) {
        //         System.out.printf("Error: %s%n", e.getMessage());
        //         interpreter.skipCommand();
        //     }
        // }

        User u  = new User("Yonas Kbrom");
        u.createWallet();
        u.createAccount("BoA");
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("BoA", 20);
        u._wallet.deposit("B", 20);
        u._wallet.withdraw("BoA", 5);
        u.createAccount("CK");
        u._wallet.transfer("CK", "BoA", 10);
//        u._wallet.accountBalance("BoA");
        System.out.println(Integer.toString(u._wallet.accountBalance("BoA")));

    }

}