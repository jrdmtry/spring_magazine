package com.example.home.demo.repositories;

import com.example.home.demo.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(a -> a.getTitle().equals(title)).findFirst().get();
    }

    public List<Product> sortCostAsc() {
        return products.stream().sorted().collect(Collectors.toList());

    }

    public List<Product> sortCostDesc() {
                return products.stream().sorted((o1, o2) -> o2.getCost() - o1.getCost()).collect(Collectors.toList());
    }

    public Product fingById(Long id) {
        return products.stream().filter(a -> a.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
            product.setId(newId);
            products.add(product);
            return;
        }
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId().equals(product.getId())) {
                p.setCost(product.getCost());
                p.setTitle(product.getTitle());
                return;
            }
        }

    }

    public void deleteById(Long id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
}
