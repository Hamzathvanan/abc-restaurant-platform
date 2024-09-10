package com.abcrestaurant.admin.user;

import com.abcrestaurant.common.entity.Role;
import com.abcrestaurant.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepoTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager entityManager;

    // Test case to create a new user with one role
    @Test
    public void testCreateUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1); // Fetch existing Admin role

        User userMike = new User("mike.jonehhs@gmail442.com", "mike2024", "Mike", "Jones");
        userMike.addRoles(roleAdmin);

        User savedUser = userRepo.save(userMike);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    // Test case to create a new user with multiple roles
    @Test
    public void testCreateUserWithMultipleRoles() {
        Role roleEditor = entityManager.find(Role.class, 3); // Fetch Editor role
        Role roleAssistant = entityManager.find(Role.class, 5); // Fetch Assistant role

        User userSara = new User("sara.jamgjges@gma22il44.com", "sara2024", "Sara", "James");
        userSara.addRoles(roleEditor);
        userSara.addRoles(roleAssistant);

        User savedUser = userRepo.save(userSara);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    // Test case to fetch all users and print to the console
    @Test
    public void testListAllUsers() {
        Iterable<User> users = userRepo.findAll();
        users.forEach(user -> System.out.println(user.toString()));
        assertThat(users).hasSizeGreaterThan(0);
    }

    // Test case to fetch a user by ID (assuming id starts from 1)
    @Test
    public void testGetUserById() {
        Integer userId = 13;
        User user = userRepo.findById(userId).orElse(null);
        assertThat(user).isNotNull();
    }

    // Test case to update a user's email and active status
    @Test
    public void testUpdateUserDetails() {
        Integer userId = 5; // Assuming this user already exists
        User user = userRepo.findById(userId).orElse(null);
        assertThat(user).isNotNull();

        user.setActive(true);
        user.setEmail("newemail.john.doe@gmail.com");
        userRepo.save(user);

        User updatedUser = userRepo.findById(userId).orElse(null);
        assertThat(updatedUser.getEmail()).isEqualTo("newemail.john.doe@gmail.com");
    }

    // Test case to update user roles by removing and adding roles
    @Test
    public void testUpdateUserRoles() {
        Integer userId = 4; // Assuming this user exists
        User user = userRepo.findById(userId).orElse(null);
        assertThat(user).isNotNull();

        Role roleSalesperson = entityManager.find(Role.class, 2); // Fetch Salesperson role
        Role roleAssistant = entityManager.find(Role.class, 5); // Fetch Assistant role

        user.getRoles().clear(); // Clear existing roles
        user.addRoles(roleSalesperson); // Assign new roles
        user.addRoles(roleAssistant);

        userRepo.save(user);

        User updatedUser = userRepo.findById(userId).orElse(null);
        assertThat(updatedUser.getRoles()).hasSize(2); // Ensure two roles are assigned
    }

    // Test case to delete a user by ID
    @Test
    public void testDeleteUserById() {
        Integer userId = 17; // Assuming this user exists
        userRepo.deleteById(userId);

        User deletedUser = userRepo.findById(userId).orElse(null);
        assertThat(deletedUser).isNull(); // Verify the user is deleted
    }

    // Test case to find a user by their email address
    @Test
    public void testFindUserByEmail() {
        String email = "sara.james@gmail.com";
        User user = userRepo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    // Test case to count the number of users by their ID
    @Test
    public void testCountUserById() {
        Integer userId = 4;
        Long countById = userRepo.countById(userId);
        assertThat(countById).isGreaterThan(0);
    }

    // Test case to disable a user (active = false)
    @Test
    public void testDisableUser() {
        Integer userId = 5;
        userRepo.updateActiveStatus(userId, false);

        User user = userRepo.findById(userId).orElse(null);
        assertThat(user.isActive()).isFalse(); // Verify the user is disabled
    }

    // Test case to enable a user (active = true)
    @Test
    public void testEnableUser() {
        Integer userId = 8; // Assuming this user exists
        userRepo.updateActiveStatus(userId, true);

        User user = userRepo.findById(userId).orElse(null);
        assertThat(user.isActive()).isTrue(); // Verify the user is enabled
    }
}
