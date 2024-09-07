package com.example.CookiesPractice.service;

import com.example.CookiesPractice.model.Product;
import com.example.CookiesPractice.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class productService {
    private productRepository operations;

    @Autowired
    public void setOperations(productRepository operations) {
        this.operations = operations;
    }
    public List<Product>sortingVariant(String columnName){
        return operations.findAll(Sort.by(columnName));
    }
    public Page<Product> getAllProducts(Pageable pageable) {
        return operations.findAll(pageable);
    }
    public Product getConcreteProduct(int id){
        return operations.findById(id).orElse(null);
    }
    public void saveProduct(Product product){
        operations.save(product);
    }
    public void updateProduct(int id,Product product){
        product.setId(id);
        operations.save(product);
    }
    public void deleteProduct(int id){
        operations.deleteById(id);
    }

    //ANYMORE
    public List<Product>getAllWherePersonIdNull(){
        return operations.findByPersonIdIsNull();
    }
}
