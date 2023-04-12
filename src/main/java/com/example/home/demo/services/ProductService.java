package com.example.home.demo.services;

import com.example.home.demo.entities.Product;
import com.example.home.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.fingById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.printAll();
    }

    public void add(Product product) {
        productRepository.saveAll(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
