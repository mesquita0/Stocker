package com.Stocker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.Stocker.entity.Product;
import com.Stocker.entity.User;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ProductRepositoryTest {
    
    ProductRepository productRepository;
    UserRepository userRepository;
    Long mockBarcode;
    String mockName;
    Product mockProduct;
    User mockUser;

    @BeforeAll
    void setup() {
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        mockBarcode = 154184L;
        mockName = "Sabonete";
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null);
        mockProduct = new Product(null, mockBarcode, mockName, 1, 5, 20, new Date(), mockUser, null);
        
        userRepository.save(mockUser);
    }

    @AfterAll
    void cleanup() {
        userRepository.delete(mockUser.getId());
    }

    @Test
    @Order(1)
    @DisplayName("Cria Produto")
    void createProduct() {
        Long id = productRepository.save(mockProduct).getId();
        
        assertNotNull(id);
        mockProduct.setId(id);
    }

    @Test
    @Order(2)
    @DisplayName("Recupera Produto com código de barra")
    void getProduct() {
        Product returnProduct = productRepository.getByBarcode(mockBarcode).get(0);

        assertEquals(returnProduct.getAmount(),        mockProduct.getAmount());
        assertEquals(returnProduct.getName(),          mockProduct.getName());
        assertEquals(returnProduct.getPurchasePrice(), mockProduct.getPurchasePrice());
        assertEquals(returnProduct.getSellingPrice(),  mockProduct.getSellingPrice());
    }

    @Test
    @Order(3)
    @DisplayName("Lista Produtos")
    void listProduct() {
        List<Product> returnProducts = productRepository.getAll(mockUser);

        assertNotEquals(returnProducts.size(), 0);
    }

    @Test
    @Order(4)
    @DisplayName("Lista Produtos por nome")
    void listProductByName() {
        List<Product> returnProducts = productRepository.getAll(
            mockUser,
            mockName.substring(0, mockName.length() - 1)
        );

        assertNotEquals(returnProducts.size(), 0);
    }

    @Test
    @Order(5)
    @DisplayName("Atualiza Produto")
    void updateProduct() {
        mockProduct.setName("test");
        productRepository.update(mockProduct);

        Product returnProduct = productRepository.getById(mockProduct.getId());
        assertEquals(returnProduct.getName(), mockProduct.getName());
    }

    @Test
    @Order(6)
    @DisplayName("Deleta Produto")
    void deleteProduct() {
        productRepository.delete(mockProduct.getId());

        Product returnProduct = productRepository.getById(mockProduct.getId());
        assertNull(returnProduct);
    }
}
