package com.Stocker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.Stocker.entity.User;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTest {
    
    UserRepository userRepository;
    String mockEmail;
    User mockUser;

    @BeforeAll
    void setup() {
        userRepository = new UserRepository();
        mockEmail = "a@gmail.com";
        mockUser = new User(null, "Pablo", "111.111.111-11", mockEmail, "123456", "(84)99999-9999", null);
    }

    @Test
    @Order(1)
    @DisplayName("Cria Usuário")
    void createUser() {        
        Long id = userRepository.save(mockUser).getId();

        assertNotNull(id);
    }

    @Test
    @Order(2)
    @DisplayName("Recupera Usuário com o email")
    void getUser() {
        User returnUser = userRepository.getByEmail(mockEmail);

        assertEquals(returnUser.getCpf(), mockUser.getCpf());
        assertEquals(returnUser.getEmail(), mockUser.getEmail());
        assertEquals(returnUser.getName(), mockUser.getName());
        assertEquals(returnUser.getPhoneNumber(), mockUser.getPhoneNumber());
    }

    @Test
    @Order(3)
    @DisplayName("Atualiza Usuário")
    void updateUser() {
        mockUser.setId(userRepository.getByEmail(mockEmail).getId());
        mockUser.setName("Lucas");
        userRepository.update(mockUser);

        User returnUser = userRepository.getByEmail(mockEmail);
        assertEquals(returnUser.getName(), mockUser.getName());
    }

    @Test
    @Order(4)
    @DisplayName("Deleta Usuário")
    void deleteUser() {
        userRepository.delete(userRepository.getByEmail(mockEmail).getId());

        User returnUser = userRepository.getByEmail(mockEmail);
        assertNull(returnUser);
    }
}
