package com.example.abdulla.hotel.model;

import java.io.Serializable;

/**
 * Created by 8422 on 29/04/16.
 */
public class Item implements Serializable {
    public String id,price;
    public String name,description;

    public Item(String id, String price, String name, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
