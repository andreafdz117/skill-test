package com.abi.restproduct.util;

import com.abi.restproduct.entity.Product;

public class ProductUtil {

    public double calculateTotal(Double price, Double quantity){
        return price * quantity;
    }

}
