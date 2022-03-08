package com.abi.restproduct;

import com.abi.restproduct.controller.ProductController;
import com.abi.restproduct.service.ProductService;
import com.abi.restproduct.util.EmailUtility;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class RestproductApplicationTests {

	@Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean                           
    private ProductService productService; 
                                       

//
//	@Test
//	void contextLoads() {
////		Assertions.assertTrue(true);
//	
//	}
	
    
    
    @DisplayName("Test Spring Controller Layer, Get Products")
    @Test
    public void getProductAPI() throws Exception {
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
    	.andExpect(status().isOk());
    }
	
    @DisplayName("Test Spring Controller Layer, Delete Products")
    @Test
    public void deleteProductAPI() throws Exception{
    	this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}", 1))
    	.andExpect(status().isOk());
    }
    
    @DisplayName("Test Spring Service Layer, find()")
    @Test
    public void findProduct() {
    	Assertions.assertTrue(!(productService.find(1).isEmpty()));
    }
    

}
