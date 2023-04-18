package com.example.home.demo.controller;

import com.example.home.demo.entities.Product;
import com.example.home.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProduct(Model model, @RequestParam(value = "word", required = false) String word) {
        Product product = new Product();
        model.addAttribute("products", productService.getAllProductsWithFilter(word));
        model.addAttribute("product", product);
        model.addAttribute("word", word);
        return "products";
    }

    @GetMapping("/sort-asc")
    public String showProductSortCostAsc() {
        productService.sortingProductCostAsc();
        return "redirect:/products";
    }

    @GetMapping("/sort-desc")
    public String showProductSortDesc() {
         productService.sortingProductCostDesc();
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "product-edit";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);

        return "product-edit";
    }

    @PostMapping("/edit")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
