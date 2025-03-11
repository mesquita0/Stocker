package com.Stocker.services;

import java.util.List;

import com.Stocker.dto.CreateProductDTO;
import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;
import com.Stocker.entity.User;
import com.Stocker.repository.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;
    private User user;

    public ProductService(User user) {
        productRepository = new ProductRepository();
        this.user = user;
    }

    public void createProduct(CreateProductDTO product) {
        Product newProduct = new Product(
            null, 
            product.getBarcode(), 
            product.getName(),
            product.getPurchasePrice(),
            product.getSellingPrice(),
            product.getAmount(),
            product.getExpiryDate(),
            user, 
            null
        );

        productRepository.save(newProduct);
    }

    public List<Product> listProducts(String name) {
        return productRepository.getAll(user, name);
    }

    public List<Product> listProducts(String name, Supplier supplier) {
        return productRepository.getAll(user, name, supplier);
    }

    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product.getId());
    }
}
