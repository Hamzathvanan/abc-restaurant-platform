package com.abcrestaurant.admin.user;

import com.abcrestaurant.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepoTest {

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("Customer", "User who can visit and create a new customer account");
        Role savedRole = roleRepo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRole() {
        Role roleSalesperson = new Role("Salesperson", "manage product price, customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders and update order status");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");

        roleRepo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
    }
}
