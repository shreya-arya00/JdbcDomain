package org.example;

import domain.Product;
import exception.ProductException;
import repository.DatabaseProductRepository;
import repository.ProductRepository;
import service.ProductService;

import java.util.Arrays;
import java.util.Collection;
// Main class to run the program
public class main {

    public static void main(String[] args) {
        // Initialize repository
        ProductRepository productRepository = new DatabaseProductRepository();

        // Initialize service
        ProductService productService = new ProductService(productRepository);

        try {
            // Create products
            Product product1 = new Product(8, "Product 1", 19.99);
            Product product2 = new Product(9, "Product 2", 29.99);

            // Add products to repository
            for (Product product3 : Arrays.asList(product1, product2)) {
                productService.createProduct(product3);
            }

            // Find product by ID
            Product foundProduct;
            foundProduct = productService.findProductById(1);
            System.out.println("Found Product by ID: " + foundProduct.getName());

            // Find products by name
            Collection<Product> productsByName = productService.findProductsByName("Product 2");
            System.out.println("Found Products by Name:");
            for (Product product : productsByName) {
                System.out.println(product.getName());
            }

            // Update product
            product2.setPrice(39.99);
            productService.updateProduct(product2);
            System.out.println("Updated Product Price: " + product2.getPrice());

            // Delete product
            productService.deleteProduct(1);
            System.out.println("Product Deleted.");

            // View all products
            Collection<Product> allProducts = productService.findAllProducts();
            System.out.println("All Products:");
            for (Product product : allProducts) {
                System.out.println(product.getName());
            }
        } catch (ProductException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
