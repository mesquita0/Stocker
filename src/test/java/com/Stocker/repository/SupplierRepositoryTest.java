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
public class SupplierRepositoryTest {
    
    SupplierRepository supplierRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    SupplierProductRepository spRepository;
    String mockName;
    Supplier mockSupplier;
    Product mockProduct;
    SupplierProduct mockSP;
    User mockUser;

    @BeforeAll
    void setup() {
        supplierRepository = new SupplierRepository();
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
        spRepository = new SupplierProductRepository();
        mockName = "teste";
        
        mockUser = new User(null, "Pablo", "111.111.111-11", "a@gmail.com", "123456", "(84)99999-9999", null, null);
        mockProduct = new Product(null, 154184L, "Sabonete", 1, 5, 20, new Date(), mockUser, null);
        mockSupplier = new Supplier(null, mockName, "111", mockUser, null);
        mockSP = new SupplierProduct(mockProduct, mockSupplier, 3, 1);

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
    @DisplayName("Cria Fornecedor")
    void createSupplier() {        
        Long id = supplierRepository.save(mockSupplier).getId();
        spRepository.save(mockSP);
        
        assertNotNull(id);
        mockSupplier.setId(id);
    }

    @Test
    @Order(2)
    @DisplayName("Recupera Fornecedor com o id")
    void getSupplier() {
        Supplier returnSupplier = supplierRepository.getById(mockSupplier.getId());

        assertEquals(returnSupplier.getName(), mockSupplier.getName());
    }

    @Test
    @Order(3)
    @DisplayName("Lista Fornecedores")
    void listSupplier() {
        List<Supplier> returnSuppliers = supplierRepository.getAll(mockUser);

        assertEquals(returnSuppliers.size(), 1);
    }

    @Test
    @Order(4)
    @DisplayName("Lista Fornecedores que não fornecem nenhum produto")
    void listSupplierWOProducts() {
        Supplier newSupplier = new Supplier(null, mockName, "111", mockUser, null);
        Long id = supplierRepository.save(newSupplier).getId(); 

        List<Supplier> returnSuppliers = supplierRepository.getAll(mockUser);
        assertEquals(returnSuppliers.size(), 2);

        supplierRepository.delete(id);

        returnSuppliers = supplierRepository.getAll(mockUser);
        assertEquals(returnSuppliers.size(), 1);   
    }

    @Test
    @Order(5)
    @DisplayName("Lista Fornecedores por nome")
    void listSupplierByName() {
        List<Supplier> returnSuppliers = supplierRepository.getAll(
            mockUser,
            mockName.substring(0, mockName.length() - 1)
        );

        assertEquals(returnSuppliers.size(), 1);
    }

    @Test
    @Order(6)
    @DisplayName("Lista Fornecedores por produto")
    void listSupplierByProduct() {
        List<Supplier> returnSuppliers = supplierRepository.getAll(mockProduct);

        assertEquals(returnSuppliers.size(), 1);
    }


    @Test
    @Order(7)
    @DisplayName("Atualiza Fornecedor")
    void updateSupplier() {
        mockSupplier.setName("test");
        supplierRepository.update(mockSupplier);

        Supplier returnSupplier = supplierRepository.getById(mockSupplier.getId());
        assertEquals(returnSupplier.getName(), mockSupplier.getName());
    }

    @Test
    @Order(8)
    @DisplayName("Deleta Fornecedor")
    void deleteSupplier() {
        supplierRepository.delete(mockSupplier.getId());
        
        Supplier returnSupplier = supplierRepository.getById(mockSupplier.getId());
        assertNull(returnSupplier);
    }
}
