package repository;

import domain.Product;
import java.util.Collection;

public interface ProductRepository {

    void create(Product product);

    void update(Product product);

    void delete(Integer productId);

    Product findById(Integer productId);

    Collection<Product> findAll();

    Collection<Product> findByField(String fieldName, String value);
}
