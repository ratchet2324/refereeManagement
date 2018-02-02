package nefra.exceptions;

public class CannotUpdateException extends Exception
{
    public CannotUpdateException() {super(); }
    public CannotUpdateException(String message) { super(message); }
    public CannotUpdateException(String message, Throwable cause) {super(message, cause); }
    public CannotUpdateException(Throwable cause) { super(cause); }
}
