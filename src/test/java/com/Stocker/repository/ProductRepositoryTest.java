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
import com.Stocker.entity.Supplier;
import com.Stocker.entity.SupplierProduct;
import com.Stocker.entity.User;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ProductRepositoryTest {
    
    ProductRepository productRepository;
    SupplierRepository supplierRepository;
    UserRepository userRepository;
    SupplierProductRepository spRepository;
    Long mockBarcode;
    String mockName;
    Product mockProduct;
    User mockUser;

    @BeforeAll
    void setup() {
        productRepository = new ProductRepository();
        supplierRepository = new SupplierRepository();
        userRepository = new UserRepository();
        spRepository = new SupplierProductRepository();
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

        assertEquals(mockProduct.getAmount(),        returnProduct.getAmount());
        assertEquals(mockProduct.getName(),          returnProduct.getName());
        assertEquals(mockProduct.getPurchasePrice(), returnProduct.getPurchasePrice());
        assertEquals(mockProduct.getSellingPrice(),  returnProduct.getSellingPrice());
    }

    @Test
    @Order(3)
    @DisplayName("Lista Produtos do usuário")
    void listProducts() {
        List<Product> returnProducts = productRepository.getAll(mockUser);

        assertEquals(1, returnProducts.size());
    }

    @Test
    @Order(4)
    @DisplayName("Não lista Produtos de outros usuários")
    void doesNotListProductsFromOtherUsers() {
        User newUser = new User(null, "test", "1", "b", "a", "c", null);
        userRepository.save(newUser);

        List<Product> returnProducts = productRepository.getAll(newUser);

        assertEquals(0, returnProducts.size());

        userRepository.delete(newUser.getId());
    }

    @Test
    @Order(5)
    @DisplayName("Lista Produtos por nome")
    void listProductByName() {
        List<Product> returnProducts = productRepository.getAll(
            mockUser,
            mockName.substring(0, mockName.length() - 1)
        );

        assertEquals(1, returnProducts.size());
        assertEquals(0, productRepository.getAll(mockUser, "p").size());
    }

    @Test
    @Order(6)
    @DisplayName("Lista Produtos por fornecedor")
    void listProductBySupplier() {
        Product newProduct    = new Product(null, 87945L, "Sabão", 10, 20, 50, new Date(), mockUser, null);
        Supplier mockSupplier  = new Supplier(null, mockName, "111", null);
        SupplierProduct mockSP = new SupplierProduct(newProduct, mockSupplier, 3, 1);

        productRepository.save(newProduct);
        supplierRepository.save(mockSupplier);
        spRepository.save(mockSP);

        assertEquals(2, productRepository.getAll(mockUser).size());

        List<Product> returnProducts = productRepository.getAll(mockUser, mockSupplier);

        assertEquals(1, returnProducts.size());
        assertEquals(newProduct.getId(), returnProducts.get(0).getId());

        assertEquals(1, productRepository.getAll(mockUser, "", mockSupplier).size());
        assertEquals(0, productRepository.getAll(mockUser, "p", mockSupplier).size());

        supplierRepository.delete(mockSupplier.getId());
        productRepository.delete(newProduct.getId());
    }

    @Test
    @Order(7)
    @DisplayName("Atualiza Produto")
    void updateProduct() {
        mockProduct.setName("test");
        productRepository.update(mockProduct);

        Product returnProduct = productRepository.getById(mockProduct.getId());
        assertEquals(mockProduct.getName(), returnProduct.getName());
    }

    @Test
    @Order(8)
    @DisplayName("Deleta Produto")
    void deleteProduct() {
        productRepository.delete(mockProduct.getId());

        Product returnProduct = productRepository.getById(mockProduct.getId());
        assertNull(returnProduct);
    }
}
