package nefra.exceptions;

public class CannotCreateException extends Exception
{
    public CannotCreateException() {super(); }
    public CannotCreateException(String message) { super(message); }
    public CannotCreateException(String message, Throwable cause) {super(message, cause); }
    public CannotCreateException(Throwable cause) { super(cause); }
}
