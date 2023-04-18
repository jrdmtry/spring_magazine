package com.example.home.demo.services;

import com.example.home.demo.entities.Product;
import com.example.home.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return productRepository.findAll();
    }

    public List<Product> getAllProductsWithFilter(String word) {
        List<Product> fullList = productRepository.findAll();
        if (word == null) {
            return fullList;
        }
        return fullList.stream().filter(p -> p.getTitle().contains(word)).collect(Collectors.toList());

    }

    public List<Product> sortingProductCostAsc() {
        return productRepository.sortCostAsc();
    }
    public List<Product> sortingProductCostDesc(){
        return productRepository.sortCostDesc();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }
}


