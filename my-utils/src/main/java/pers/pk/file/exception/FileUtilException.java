package pers.pk.file.exception;

public class FileUtilException extends Throwable{

    private String msg;
    private Exception exception;

    public FileUtilException(String msg, Exception exception) {
        this.msg = msg;
        this.exception = exception;
    }
}
