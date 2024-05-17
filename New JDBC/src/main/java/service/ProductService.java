package service;

import domain.Product;
import exception.ProductException;
import repository.ProductRepository;

import java.util.Collection;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        if (product.getId() == null || product.getId() == 0) {
            throw new ProductException("Product ID cannot be null or empty.");
        }
        productRepository.create(product);
    }

    public void deleteProduct(Integer productId) {
        Product existingProduct = productRepository.findById(productId);
        if (existingProduct == null) {
            throw new ProductException("Product not found with ID: " + productId);
        }
        productRepository.delete(productId);
    }

    public Product findProductById(Integer productId) {
        Product product;
        product = productRepository.findById(productId);
        if (product == null) {
            throw new ProductException("Product not found with ID: " + productId);
        }
        return product;
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Collection<Product> findProductsByName(String name) {
        return productRepository.findByField("name", name);
    }

    public void updateProduct(Product product) {
        if (productRepository.findById(product.getId()) == null) {
            throw new ProductException("Product not found with ID: " + product.getId());
        }
        productRepository.update(product);
    }
}
