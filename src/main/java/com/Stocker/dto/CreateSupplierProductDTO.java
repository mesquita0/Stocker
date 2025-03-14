package com.Stocker.dto;

import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;

public class CreateSupplierProductDTO {
    private Supplier supplier;
    private Product product;
    private Integer deliveryTime, price;


    public CreateSupplierProductDTO(Supplier supplier, Product product, Integer deliveryTime, Integer price) {
        this.supplier = supplier;
        this.product = product;
        this.deliveryTime = deliveryTime;
        this.price = price;
    }


    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
