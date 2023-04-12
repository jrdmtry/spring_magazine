package com.example.home.demo.repositories;

import com.example.home.demo.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public List<Product> printAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(a -> a.getTitle().equals(title)).findFirst().get();
    }

    public Product fingById(Long id) {
        return products.stream().filter(a -> a.getId().equals(id)).findFirst().get();
    }

    public void saveAll(Product product) {
        products.add(product);
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
