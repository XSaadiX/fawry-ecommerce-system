/**
 * Custom exception for e-commerce operations.
 */
public class ECommerceException extends Exception {

    public ECommerceException(String message) {
        super(message);
    }

    public ECommerceException(String message, Throwable cause) {
        super(message, cause);
    }
}