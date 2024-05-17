package exception;

import java.sql.SQLException;

// Custom exception for product-related errors
public class ProductException extends RuntimeException {
    public ProductException(String message) {
        super(message);
    }

    public ProductException(String errorWhileMappingProduct, SQLException e) {
    }
}
