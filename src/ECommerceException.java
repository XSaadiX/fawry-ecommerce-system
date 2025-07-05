// Custom exception for e-commerce business logic errors
class ECommerceException extends Exception {

    public ECommerceException(String message) {
        super(message);
    }
}