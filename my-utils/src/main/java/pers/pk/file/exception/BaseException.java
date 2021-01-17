package pers.pk.file.exception;

public class BaseException extends Throwable{

    private String msg;
    private Exception exception;

    public BaseException(String msg, Exception exception) {
        this.msg = msg;
        this.exception = exception;
    }
}
