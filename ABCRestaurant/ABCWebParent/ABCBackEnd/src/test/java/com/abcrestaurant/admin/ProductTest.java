package com.abcrestaurant.admin;

import com.abcrestaurant.admin.product.ProductRepository;
import com.abcrestaurant.common.entity.Category;
import com.abcrestaurant.common.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // to use actual DB
@Rollback(false)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateProducts() {
        Category category = entityManager.find(Category.class, 1); // Assuming category with ID 1 exists
        Product product1 = new Product();
        product1.setName("Chicken Biryani");
        product1.setPrice(8.99f);
        product1.setCategory(category);
        product1.setImage("chicken-biryani.jpg");

        Product product2 = new Product();
        product2.setName("Beef Burger");
        product2.setPrice(5.99f);
        product2.setCategory(category);
        product2.setImage("beef-burger.jpg");

        productRepository.saveAll(List.of(product1, product2));

        assertThat(product1.getId()).isGreaterThan(0);
        assertThat(product2.getId()).isGreaterThan(0);
    }

    @Test
    public void testListProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        products.forEach(System.out::println);

        assertThat(products).hasSizeGreaterThan(0);
    }

    @Test
    public void testGetProductById() {
        Integer productId = 1; // Assuming product with ID 1 exists
        Product product = productRepository.findById(productId).get();

        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Chicken Biryani");
    }

    @Test
    public void testUpdateProduct() {
        Integer productId = 1; // Assuming product with ID 1 exists
        Product product = productRepository.findById(productId).get();
        product.setPrice(9.99f);

        productRepository.save(product);

        Product updatedProduct = productRepository.findById(productId).get();
        assertThat(updatedProduct.getPrice()).isEqualTo(9.99f);
    }

    @Test
    public void testDeleteProduct() {
        Integer productId = 2; // Assuming product with ID 2 exists
        productRepository.deleteById(productId);

        assertThat(productRepository.findById(productId)).isEmpty();
    }
}