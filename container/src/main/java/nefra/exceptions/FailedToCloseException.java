package nefra.exceptions;

public class FailedToCloseException extends Exception {
    public FailedToCloseException() {super(); }
    public FailedToCloseException(String message) { super(message); }
    public FailedToCloseException(String message, Throwable cause) {super(message, cause); }
    public FailedToCloseException(Throwable cause) { super(cause); }
}
