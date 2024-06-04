package exercise.exception;

public class AuthorNotFoundForBookException extends RuntimeException {
    public AuthorNotFoundForBookException(String message) {
        super(message);
    }
}
