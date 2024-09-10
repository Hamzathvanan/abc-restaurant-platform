package com.abcrestaurant.admin;

import com.abcrestaurant.admin.category.CategoryService;
import com.abcrestaurant.admin.product.ProductService;
import com.abcrestaurant.common.entity.Category;
import com.abcrestaurant.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/customer")
    public String viewHomePage(Model model) {
        List<Category> listCategories = categoryService.listAll();
        List<Product> listProducts = productService.listAll();

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listProducts", listProducts);

        return "customer/customer"; // Thymeleaf template for home page (visitor/customer)
    }

    @GetMapping("/customer/{categoryId}")
    public String viewCategoryProducts(@PathVariable("categoryId") Integer categoryId, Model model) {
        Category category = categoryService.get(categoryId);
        List<Product> products = productService.listByCategory(category);

        model.addAttribute("category", category);
        model.addAttribute("products", products);

        // Check if no products for the category
        if (products.isEmpty()) {
            model.addAttribute("noProducts", true);  // To show a popup or message
        } else {
            model.addAttribute("noProducts", false);
        }

        return "customer/products_by_category";  // New Thymeleaf template to display products
    }
}
