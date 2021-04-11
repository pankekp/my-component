package pers.pk.util.exception;

public class UtilException extends Throwable {

    private String msg;
    private Exception exception;

    public UtilException(String msg, Exception exception) {
        this.msg = msg;
        this.exception = exception;
    }

    public String getMsg() {
        return msg;
    }

    public Exception getException() {
        return exception;
    }
}
