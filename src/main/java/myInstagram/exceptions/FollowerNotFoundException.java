package myInstagram.exceptions;

public class FollowerNotFoundException extends RuntimeException {
    public FollowerNotFoundException(String message) {
        super(message);
    }
}
