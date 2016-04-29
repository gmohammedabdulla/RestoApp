package com.example.abdulla.hotel.model;

import java.io.Serializable;

/**
 * Created by 8422 on 30/04/16.
 */
public class CartItem extends Item implements Serializable {
    public int quantity;
    public CartItem(String id, String price, String name, String description,int quantity){
        super(id,price,name,description);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
