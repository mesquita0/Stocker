package com.Stocker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.Stocker.entity.Supplier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SupplierRepositoryTest {
    
    SupplierRepository supplierRepository;
    String mockName;
    Supplier mockSupplier;

    @BeforeAll
    void setup() {
        supplierRepository = new SupplierRepository();
        mockName = "teste";
        mockSupplier = new Supplier(null, mockName, "111", null);
    }

    @Test
    @Order(1)
    @DisplayName("Cria Fornecedor")
    void createSupplier() {        
        Long id = supplierRepository.save(mockSupplier);
        
        assertNotNull(id);
        mockSupplier.setId(id);
    }

    @Test
    @Order(2)
    @DisplayName("Recupera Fornecedor com o id")
    void getSupplier() {
        Supplier returnSupplier = supplierRepository.get(mockSupplier.getId());

        assertEquals(returnSupplier.getName(), mockSupplier.getName());
    }

    @Test
    @Order(3)
    @DisplayName("Lista Fornecedores")
    void listSupplier() {
        List<Supplier> returnSuppliers = supplierRepository.getAll();

        assertNotEquals(returnSuppliers.size(), 0);
    }

    @Test
    @Order(4)
    @DisplayName("Lista Fornecedores por nome")
    void listSupplierByName() {
        List<Supplier> returnSuppliers = supplierRepository.getAll(
            mockName.substring(0, mockName.length() - 1)
        );

        assertNotEquals(returnSuppliers.size(), 0);
    }

    @Test
    @Order(5)
    @DisplayName("Atualiza Fornecedor")
    void updateSupplier() {
        mockSupplier.setId(supplierRepository.get(mockSupplier.getId()).getId());
        mockSupplier.setName("Lucas");
        supplierRepository.update(mockSupplier);

        Supplier returnSupplier = supplierRepository.get(mockSupplier.getId());
        assertEquals(returnSupplier.getName(), mockSupplier.getName());
    }

    @Test
    @Order(6)
    @DisplayName("Deleta Fornecedor")
    void deleteSupplier() {
        supplierRepository.delete(supplierRepository.get(mockSupplier.getId()).getId());

        Supplier returnSupplier = supplierRepository.get(mockSupplier.getId());
        assertEquals(returnSupplier, null);
    }
}
