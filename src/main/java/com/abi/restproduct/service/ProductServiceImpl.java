package com.abi.restproduct.service;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> read() {
        log.info("Se leen todos los productos.");
        return productRepository.findAll();
    }
    

    // TODO: Agregar los metodos CRUD faltantes

	@Override
	public Product save(Product product) {
		
		log.info("Se agrega o actualiza un nuevo producto.");
		
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> find(int id) {
		log.info("Se busca el producto con id: "+id);
		return productRepository.findById(id);		
	}


	@Override
	public void delete(int id) {
		log.info("Se elimina el producto con id: "+id);		
		productRepository.deleteById(id);
	}

	
	
	


	
	



    
   

}
