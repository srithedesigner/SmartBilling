package com.vaishu.smartbilling.Classes;

public class CartElement {
    int quantity;
    Product p;

    public CartElement(int quantity, Product p) {
        this.quantity = quantity;
        this.p = p;
    }

    public CartElement() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
}
