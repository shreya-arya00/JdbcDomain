package repository;

import domain.Product;
import exception.ProductException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseProductRepository implements ProductRepository {

    @Override
    public void create(Product product) {
        DatabaseUtil.execute("INSERT INTO products (id, name, price) VALUES (?, ?, ?)",
                product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public void update(Product product) {
        DatabaseUtil.execute("UPDATE products SET name = ?, price = ? WHERE id = ?",
                product.getName(), product.getPrice(), product.getId());
    }

    @Override
    public void delete(Integer productId) {
        DatabaseUtil.execute("DELETE FROM products WHERE id = ?", productId);
    }

    @Override
    public Product findById(Integer productId) {
        return DatabaseUtil.findOne("SELECT * FROM products WHERE id = ?",
                resultSet -> {
                    try {
                        return new Product(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getDouble("price"));
                    } catch (SQLException e) {
                        throw new ProductException("Error while mapping product", e);
                    }
                },
                productId);
    }

    @Override
    public Collection<Product> findAll() {
        return DatabaseUtil.findMany("SELECT * FROM products",
                resultSet -> {
                    try {
                        return new Product(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getDouble("price"));
                    } catch (SQLException e) {
                        throw new ProductException("Error while mapping products", e);
                    }
                });
    }

    @Override
    public Collection<Product> findByField(String fieldName, String value) {
        // Example query: SELECT * FROM products WHERE fieldName = value
        // Implementation omitted for brevity
        return new ArrayList<>();
    }
}
