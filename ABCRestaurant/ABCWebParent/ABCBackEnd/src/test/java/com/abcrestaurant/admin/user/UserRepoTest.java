package com.abcrestaurant.admin.user;

import com.abcrestaurant.common.entity.Role;
import com.abcrestaurant.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepoTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userNameHM = new User("ham@abc.net", "ham2024", "Hamzath", "vanan");
        userNameHM.addRoles(roleAdmin);

        User savedUser = userRepo.save(userNameHM);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User userRavi = new User("ravi@gmail.com", "ravi2024", "Ravi", "Kumar");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userRavi.addRoles(roleEditor);
        userRavi.addRoles(roleAssistant);

        User savedUser = userRepo.save(userRavi);
        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = userRepo.findAll();
        listUsers.forEach(System.out::println);
    }

    @Test
    public void testGetUserById() {
        User userHam = userRepo.findById(1).get();
        System.out.println(userHam);
        assertThat(userHam).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userHam = userRepo.findById(1).get();
        userHam.setActive(true);
        userHam.setEmail("hamzathvanan@gmail.com");

        userRepo.save(userHam);
    }

    @Test
    public void testUpdateUserRoles() {
        User userRavi = userRepo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRoles(roleSalesperson);

        userRepo.save(userRavi);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 2;
        userRepo.deleteById(userId);
    }
}
