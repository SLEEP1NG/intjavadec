package exceptions;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class BetterModemExample {

    static class ModemDidNotConnectException extends Exception {}
    public static void dialModem(int num) throws ModemDidNotConnectException { }

    private static boolean USE_MODEM = false;

    static class RetryCCLaterException extends Exception {
        public RetryCCLaterException() {
        }

        public RetryCCLaterException(String message) {
            super(message);
        }

        public RetryCCLaterException(String message, Throwable cause) {
            super(message, cause);
        }

        public RetryCCLaterException(Throwable cause) {
            super(cause);
        }

        public RetryCCLaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
    static class DeclinedCCException extends Exception {}

    public static void authorizePayment(int ccnum, int amt)
            throws RetryCCLaterException, DeclinedCCException {
        int retries = 3;
        try {
            if (USE_MODEM) {
                dialModem(123456);
            } else {
                Socket s = new Socket("127.0.0.1", 8080);
            }
        } catch (ModemDidNotConnectException | IOException ex) {
            if (--retries == 0) throw new RetryCCLaterException("the system isn't working", ex);
        }
    }

    public static void sellSomething() {
        try {
            authorizePayment(1, 1000);
        } catch (RetryCCLaterException t) {
            // advise user "maybe later";
        } catch (DeclinedCCException dcce) {
            // not a chance...
        }
    }
}
