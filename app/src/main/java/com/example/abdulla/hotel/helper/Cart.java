package com.example.abdulla.hotel.helper;

import com.example.abdulla.hotel.model.CartItem;
import com.example.abdulla.hotel.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 8422 on 30/04/16.
 */
public class Cart implements Serializable{
    private static Cart instance = null;
    private HashMap<String,CartItem> cart = new HashMap<String,CartItem>();

    private Cart(){
    }
    public static Cart getInstance(){
        if (instance == null){
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(String menuId,Item item,int quantity){
        CartItem cartItem = new CartItem(item.getId(),item.getPrice(),item.getName(),item.getDescription(),quantity);
        cart.put(item.getId(),cartItem);
    }

    public ArrayList<CartItem> getItems(){
        ArrayList<CartItem> cartItemsList = new ArrayList<CartItem>();
        for(String key:cart.keySet()){
            cartItemsList.add(cart.get(key));
        }
        return cartItemsList;
    }



}
