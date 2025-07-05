// Custom exception for e-commerce business logic errors
class ECommerceException extends Exception {

    ECommerceException(String message) {
        super(message);
    }
}