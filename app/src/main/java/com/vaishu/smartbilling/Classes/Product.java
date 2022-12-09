package com.vaishu.smartbilling.Classes;

public class Product {

    String productName;
    float price;
    String barcode;

    public Product(String productName, float price, String barcode) {
        this.productName = productName;
        this.price = price;
        this.barcode = barcode;
    }

    public Product() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
