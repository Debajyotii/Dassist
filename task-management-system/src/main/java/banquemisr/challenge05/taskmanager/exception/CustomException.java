package banquemisr.challenge05.taskmanager.exception;

public class CustomException extends RuntimeException {
    public CustomException(String s) {
        super(s);
    }

    public CustomException(String s, Exception e) {
        super(s, e);
    }
}
