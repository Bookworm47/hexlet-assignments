package exercise;

// BEGIN
class NegativeRadiusException extends Exception {
    String exception;

    NegativeRadiusException(String exception) {
        this.exception = exception;
    }
    NegativeRadiusException() {
    }
}
// END
