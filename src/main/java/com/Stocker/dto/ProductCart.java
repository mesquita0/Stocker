package com.Stocker.dto;

import java.util.Objects;

import com.Stocker.entity.Product;

public class ProductCart {
    private Product product;
    private int amount;

    public ProductCart(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product.getId());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ProductCart)) return false;
        ProductCart o = (ProductCart) other;

        return (product.getId() == o.getProduct().getId());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }            
}
