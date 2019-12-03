package exceptions;

import java.io.IOException;
import java.net.Socket;

public class ModemExample {

    class ModemDidNotConnectException extends Exception {}
    public static void dialModem(int num) throws ModemDidNotConnectException { }

    private static boolean USE_MODEM = false;

    public static void authorizePayment(int ccnum, int amt)
            throws ModemDidNotConnectException, IOException {
        int retries = 3;
        try {
            if (USE_MODEM) {
                dialModem(123456);
            } else {
                Socket s = new Socket("127.0.0.1", 8080);
            }
        } catch (ModemDidNotConnectException | IOException ex) {
            if (--retries == 0) throw ex;
        }
    }

    public static void sellSomething() {
        try {
            authorizePayment(1, 1000);
        } catch (ModemDidNotConnectException | IOException t) {
            throw new RuntimeException(t);
        }
    }
}
