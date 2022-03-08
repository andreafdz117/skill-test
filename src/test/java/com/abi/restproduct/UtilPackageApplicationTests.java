package com.abi.restproduct;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.abi.restproduct.util.EmailUtility;

class UtilPackageApplicationTests {

	@DisplayName("Test Spring paquete util")
	@Test
	public void checkUtilityPackage() {
    	
    	EmailUtility emailUtility = new EmailUtility();
    
    	Assertions.assertTrue(emailUtility.ocultar("hgonzalez@gmail.com") == "h*****z@gmail.com");
    	
	}
    
}
