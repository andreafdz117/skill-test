package com.abi.restproduct.service;

import com.abi.restproduct.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> read();
    
    // TODO: Agregar los metodos CRUD faltantes
    
    Product save(Product product);
    
    Optional<Product> find(int id);
    
    void delete(int id);
}
