package com.example.prm_groupproject_shop.Model;

public class CartItem {
    private String productId;
    private String productName;
    private String productDes;
    private double price;
    private int quantity;

    public CartItem(String productId, String productName, String productDes, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productDes = productDes;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
}
