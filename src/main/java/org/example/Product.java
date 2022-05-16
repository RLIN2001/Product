package org.example;

public class Product implements Comparable<Product>{
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


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }

}