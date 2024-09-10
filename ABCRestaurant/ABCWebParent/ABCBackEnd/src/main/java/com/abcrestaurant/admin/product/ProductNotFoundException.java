package com.abcrestaurant.admin.product;

public class ProductNotFoundException  extends Throwable {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
