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

import com.Stocker.dto.Notification;
import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;
import com.Stocker.entity.SupplierProduct;
import com.Stocker.entity.User;
import com.Stocker.repository.ProductRepository;
import com.Stocker.repository.SaleRepository;
import com.Stocker.repository.SupplierProductRepository;
import com.Stocker.repository.SupplierRepository;
import com.Stocker.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class NotificationServiceTest {
    
    NotificationService notificationService;
    SaleService saleService;
    SaleRepository saleRepository;
    SupplierRepository supplierRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    SupplierProductRepository spRepository;
    Supplier mockSupplier;
    Product mockProduct;
    SupplierProduct mockSP;
    User mockUser;

    @BeforeAll
    void setup() {
        saleRepository = new SaleRepository();
        supplierRepository = new SupplierRepository();
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        spRepository = new SupplierProductRepository();
        
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null, null);
        mockProduct = new Product(null, 154184L, "Sabonete", 1, 5, 24, new Date(), mockUser, null);
        mockSupplier = new Supplier(null, "teste", "111", mockUser,  null);
        mockSP = new SupplierProduct(mockProduct, mockSupplier, 3, 1);

        userRepository.save(mockUser);
        productRepository.save(mockProduct);
        supplierRepository.save(mockSupplier);
        spRepository.save(mockSP);

        notificationService = new NotificationService(mockUser);
        saleService = new SaleService(mockUser);
    }

    @AfterEach
    void cleanSales() {
        saleRepository.getAll(mockUser).forEach(s -> saleRepository.delete(s.getId()));
    }

    @AfterAll
    void cleanup() {
        supplierRepository.delete(mockSupplier.getId());
        productRepository.delete(mockProduct.getId());
        userRepository.delete(mockUser.getId());
    }

    @Test
    @Order(1)
    @DisplayName("Retorna notificação de produto com baixo estoque")
    void notifiesProductLowStock() {        
        saleService.addToCart(mockProduct, 5);
        saleService.addToCart(mockProduct, 7);
        saleService.addToCart(mockProduct, 6);

        saleService.confirmSale();

        List<Notification> notifications = notificationService.execute();

        assertEquals(1, notifications.size());
        
        Notification notification = notifications.get(0);

        // 18 venda nos últimos 7 dias ~ 2.57 por dia
        // ceil(6 / 2.57) = 3 dias sobrando de estoque
        assertEquals(3, notification.getMinDeliveryTime());
        assertEquals(3, notification.getNumDaysRemainingStock());
        assertEquals(18.0/7, notification.getSalesPerDay());
        assertEquals(mockProduct.getId(), notification.getProduct().getId());
    }

    @Test
    @Order(2)
    @DisplayName("Não retorna notificação de produto com estoque suficiente")
    void doesNotNotifyProductHighStock() {      
        mockProduct.setAmount(4);
        productRepository.update(mockProduct);
        
        saleService.addToCart(mockProduct, 2);
        saleService.confirmSale();

        List<Notification> notifications = notificationService.execute();

        // 2 venda nos últimos 7 dias ~ 0.28 por dia
        // ceil(4 / 0.28) = 14 dias sobrando de estoque
        assertEquals(0, notifications.size());
    }

    @Test
    @Order(3)
    @DisplayName("Não retorna notificação de produto sem nenhuma venda recente")
    void doesNotNotifyProductWOSales() {
        List<Notification> notifications = notificationService.execute();
        assertEquals(0, notifications.size());
    }
}
