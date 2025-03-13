package com.Stocker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.Stocker.entity.Sale;
import com.Stocker.entity.User;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SaleRepositoryTest {
    
    SaleRepository saleRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    Sale mockSale;
    Product mockProduct;
    User mockUser;

    @BeforeAll
    void setup() {
        saleRepository = new SaleRepository();
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null, null);
        mockProduct = new Product(null, 154184L, "Sabonete", 1, 5, 20, new Date(), mockUser, null);
        mockSale = new Sale(null, 1, mockProduct.getSellingPrice(), new Date(), mockProduct);

        userRepository.save(mockUser);
        productRepository.save(mockProduct);
    }

    @AfterAll
    void cleanup() {
        productRepository.delete(mockProduct.getId());
        userRepository.delete(mockUser.getId());
    }

    @Test
    @Order(1)
    @DisplayName("Cria venda")
    void createSale() {        
        Long id = saleRepository.save(mockSale).getId();

        assertNotNull(id);
        mockSale.setId(id);
    }

    @Test
    @Order(2)
    @DisplayName("Lista Vendas")
    void listSales() {
        List<Sale> returnSales = saleRepository.getAll(mockUser);

        assertEquals(returnSales.size(), 1);
    }

    @Test
    @Order(3)
    @DisplayName("Não lista Vendas de outros usuários")
    void doesNotListSalesOtherUsers() {
        User newUser = new User(null, "Lucas", "111.111.111-12", "ab@gmail.com", "1234567", "(84)99999-9998", null, null);
        Long newUserId = userRepository.save(newUser).getId();

        List<Sale> returnSales = saleRepository.getAll(newUser);

        assertEquals(returnSales.size(), 0);

        userRepository.delete(newUserId);
    }

    @Test
    @Order(4)
    @DisplayName("Atualiza Venda")
    void updateSale() {
        mockProduct.setName("test");
        productRepository.update(mockProduct);

        Product returnProduct = productRepository.getById(mockProduct.getId());
        assertEquals(returnProduct.getName(), mockProduct.getName());
    }

    @Test
    @Order(5)
    @DisplayName("Deleta Venda")
    void deleteSale() {
        saleRepository.delete(mockSale.getId());

        Sale returnSale = saleRepository.getById(mockSale.getId());
        assertNull(returnSale);
    }
}
