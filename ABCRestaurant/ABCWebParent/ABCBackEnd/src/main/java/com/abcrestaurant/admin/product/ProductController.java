package com.abcrestaurant.admin.product;

import com.abcrestaurant.admin.FileUploadUtil;
import com.abcrestaurant.admin.category.CategoryService;
import com.abcrestaurant.common.entity.Category;
import com.abcrestaurant.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Display list of all products
    @GetMapping("/products")
    public String listAllProducts(Model model) {
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products"; // HTML page for displaying product list
    }

    // Show product creation form
    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        List<Category> listCategories = categoryService.listAll();
        Product product = new Product();

        model.addAttribute("product", product);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle", "Create New Product");

        return "product_form"; // HTML form for new product
    }

    // Handle saving new or updated product
    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            product.setImage(fileName);
            Product savedProduct = productService.save(product);

            String uploadDir = "ABCWebParent/ABCBackEnd/src/main/resources/static/images/product-images/" + savedProduct.getId();

            FileUploadUtil.cleanDir(uploadDir); // Delete old images
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile); // Save new image
        } else {
            if (product.getImage().isEmpty()) product.setImage(null);
            productService.save(product);
        }

        redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
        return "redirect:/products"; // Redirect back to product list
    }

    // Show product edit form
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.get(id);
            List<Category> listCategories = categoryService.listAll();

            model.addAttribute("product", product);
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");

            return "product_form"; // HTML form for editing product
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }

    // Delete a product by ID
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The product ID " + id + " has been deleted successfully.");
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
    }
}
