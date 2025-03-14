package com.Stocker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.Stocker.entity.Product;
import com.Stocker.entity.Sale;
import com.Stocker.entity.User;
import com.Stocker.repository.ProductRepository;
import com.Stocker.repository.SaleRepository;
import com.Stocker.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SaleServiceTest {
    
    SaleService saleService;
    SaleRepository saleRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    Product mockProduct;
    User mockUser;
    int initialAmountProduct = 24;

    @BeforeAll
    void setup() {
        saleRepository = new SaleRepository();
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null, null);
        mockProduct = new Product(null, 87945L, "Sabão", 10.0, 20.0, 50, new Date(), mockUser, null);
                
        userRepository.save(mockUser);
        productRepository.save(mockProduct);

        saleService = new SaleService(mockUser);
    }

    @AfterEach
    void cleanSales() {
        saleRepository.getAll(mockUser).forEach(s -> saleRepository.delete(s.getId()));
    }

    @AfterAll
    void cleanup() {
        productRepository.delete(mockProduct.getId());
        userRepository.delete(mockUser.getId());
    }

    @Test
    @Order(1)
    @DisplayName("Adiciona, remove e atualiza produtos no carrinho")
    void managesCart() {      
        assertEquals(0, saleService.getProductsCart().size());  
        
        saleService.addToCart(mockProduct, 7);
        assertEquals(1, saleService.getProductsCart().size());
        assertEquals(7, saleService.getProductsCart().get(0).getAmount());
        assertEquals(initialAmountProduct - 7, mockProduct.getAmount());
        
        saleService.removeFromCart(mockProduct);
        assertEquals(0, saleService.getProductsCart().size());
        assertEquals(initialAmountProduct, mockProduct.getAmount());
        
        saleService.addToCart(mockProduct, 7);
        saleService.updateAmount(mockProduct, 5);
        assertEquals(1, saleService.getProductsCart().size());
        assertEquals(5, saleService.getProductsCart().get(0).getAmount());
        assertEquals(initialAmountProduct - 5, mockProduct.getAmount());
        
        saleService.clearCart();
        assertEquals(0, saleService.getProductsCart().size());  
        assertEquals(initialAmountProduct, mockProduct.getAmount());
        
        saleService.addToCart(mockProduct, 1);
        saleService.addToCart(mockProduct, 3);
        saleService.addToCart(mockProduct, 2);
        assertEquals(1, saleService.getProductsCart().size());
        assertEquals(6, saleService.getProductsCart().get(0).getAmount());
        assertEquals(initialAmountProduct - 6, mockProduct.getAmount());
        
        saleService.clearCart();
    }

    @Test
    @Order(2)
    @DisplayName("Não adiciona no carrinho mais items do que disponível no estoque")
    void doesNotAddToCart() {        
        saleService.addToCart(mockProduct, 25);

        assertEquals(0, saleService.getProductsCart().size());
    }

    @Test
    @Order(3)
    @DisplayName("Cria uma venda e atualiza a quantidade do produto no estoque")
    void createSale() {        
        int prevAmount = mockProduct.getAmount();

        saleService.addToCart(mockProduct, 5);
        saleService.confirmSale();

        assertEquals(prevAmount - 5, mockProduct.getAmount());
        
        List<Sale> sales = saleRepository.getAll(mockUser);
        
        assertEquals(1, sales.size());
        
        Sale sale = sales.get(0);
        
        assertEquals(5, sale.getAmount());
        assertEquals(mockProduct.getSellingPrice(), sale.getPrice());
        assertEquals(mockProduct.getId(), sale.getProduct().getId());
    }

    @Test
    @Order(4)
    @DisplayName("Lista vendas do usuário em ordem da mais recente para a mais antiga")
    void listSales() {        
        assertEquals(0, saleService.listSales().size());

        saleService.addToCart(mockProduct, 2);
        saleService.confirmSale();
        
        // Esperar um pouco para que as datas das vendas sejam diferentes
        try { 
            Thread.sleep(1000); 
        }
        catch (Exception e) {}

        saleService.addToCart(mockProduct, 1);
        saleService.confirmSale();

        List<Sale> sales = saleService.listSales();

        assertEquals(2, sales.size());
        assertEquals(1, sales.get(0).getAmount());
        assertEquals(2, sales.get(1).getAmount());
    }
}
