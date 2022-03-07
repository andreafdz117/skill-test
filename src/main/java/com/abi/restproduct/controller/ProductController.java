package com.abi.restproduct.controller;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        String message = "Service Product working";
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> read(){
        List<Product> productList = productService.read();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // TODO: Agregar los metodos CRUD faltantes

}
