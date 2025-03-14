package com.Stocker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.Stocker.entity.Product;
import com.Stocker.entity.User;
import com.Stocker.repository.ProductRepository;
import com.Stocker.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SupplierServiceTest {
    
    ProductService productService;
    ProductRepository productRepository;
    UserRepository userRepository;
    Product mockProduct;
    User mockUser;

    @BeforeAll
    void setup() {
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null, null);
        mockProduct = new Product(null, 154184L, "Sabonete", 1, 5, 24, new Date(), mockUser, null);

        userRepository.save(mockUser);
        productRepository.save(mockProduct);

        productService = new ProductService(mockUser);
    }

    @AfterAll
    void cleanup() {
        productRepository.delete(mockProduct.getId());
        userRepository.delete(mockUser.getId());
    }

    @Test
    @Order(2)
    @DisplayName("")
    void listProducts() {
        List<Product> products = productService.listProducts("");
        assertEquals(1, products.size());
    }
}
