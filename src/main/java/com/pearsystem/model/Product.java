package com.pearsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_price")
    private double product_price;
    @Column(name = "stock")
    private boolean stock;
    @Column(name = "product_quantity")
    private int product_quantity;
    @Column(name = "live")
    private boolean live;
    @Column(name = "product_imageName")
    private  String product_imageName;
    @Column(name = "product_description")
    private  String product_description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getProduct_imageName() {
        return product_imageName;
    }

    public void setProduct_imageName(String product_imageName) {
        this.product_imageName = product_imageName;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }
}
