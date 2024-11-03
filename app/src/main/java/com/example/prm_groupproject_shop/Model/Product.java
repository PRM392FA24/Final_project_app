package com.example.prm_groupproject_shop.Model;

public class Product {
    private String productId;
    private String productName;
    private String produtDesription;
    private int quantity;
    private double price;

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
    public String getProdutDesription() {
        return produtDesription;
    }
    public void setProdutDesription(String produtDesription) {
        this.produtDesription = produtDesription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
