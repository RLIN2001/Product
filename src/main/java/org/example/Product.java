package org.example;

public class Product {
    int id;
    String name;
    Double price;
    int quantity;
    public Product(int id,
                   String name,
                   Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}