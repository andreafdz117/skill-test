package com.abi.restproduct.controller;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    
    private ObjectMapper mapper = new ObjectMapper();
    
    private ObjectNode response = mapper.createObjectNode();
    
    private String json;

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        String message = "Service Product working";
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> read(){
        List<Product> productList = productService.read();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    

    // TODO: Agregar los metodos CRUD faltantes
    
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) throws JsonProcessingException{
    	 
    	response = mapper.createObjectNode();
    	
    	try {
    		if (!bindingResult.hasErrors()) {
	    		Product _product = productService.save(product);
				    		
	    		response.put("product", mapper.writeValueAsString(_product));
	    		response.put("message", "El producto se agrego correctamente");
	    		response.put("status", HttpStatus.CREATED.getReasonPhrase());
	    		response.put("code", HttpStatus.CREATED.value());
	    		
	    		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
	    		
	    		return new ResponseEntity<>(json, HttpStatus.CREATED);
	    		
    		}else {   			
    			response.put("product", mapper.writeValueAsString(product));
	    		response.put("message", "Verifique el producto");
	    		response.put("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
	    		response.put("code", HttpStatus.BAD_REQUEST.value());
	    		
	    		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
	    		
	    		return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    			
    		}
		} catch (Exception e) {
			log.info("Error: "+ e);
			response.put("message", "No hemos podido agregar el producto, intentelo de nuevo");
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());  		
			
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response); 					
			return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws JsonProcessingException{
    	
    	response = mapper.createObjectNode();
    	
    	Optional<Product> productData = productService.find(id);
    	
    	if (productData.isPresent()) {
			
    		Product _product = productData.get();
			_product.setDescription(product.getDescription());
			_product.setName(product.getName());
			_product.setPrice(product.getPrice());
			_product.setQuantity(product.getQuantity());
			
			response.put("product", mapper.writeValueAsString(productService.save(_product)));
    		response.put("message", "El producto se actualizó correctamente");
    		response.put("status", HttpStatus.OK.getReasonPhrase());
    		response.put("code", HttpStatus.OK.value());
    		
    		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
    		return new ResponseEntity<>(json, HttpStatus.OK);
			
		} else {
			String errorMessage = "No se encontro el producto";
			log.info("Error: "+ errorMessage);
			response.put("message", errorMessage+", intentelo de nuevo");
			response.put("status", HttpStatus.NOT_FOUND.getReasonPhrase());	
			response.put("code", HttpStatus.NOT_FOUND.value());
			
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
			return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);			
		}
    }
    
    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) throws JsonProcessingException{
    	 
    	response = mapper.createObjectNode();
    	
    	try {
    		
    		productService.delete(id);
    		log.info("El producto se elimino correctamente");
    		
    		response.put("message", "El producto se eliminó correctamente");
    		response.put("status", HttpStatus.OK.getReasonPhrase());
    		response.put("code", HttpStatus.OK.value());
    		
    		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    		
    		return new ResponseEntity<>(json,HttpStatus.OK); 		
			
		} catch (Exception e) {
			log.info("Error: "+ "e");
			response.put("message", "No hemos podido eliminar el producto");
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());	
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());		
			
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
			
			return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    }
    
    

}
