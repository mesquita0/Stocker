package com.Stocker.dto;

import com.Stocker.entity.Product;

public class Notification {
    private Product product;
    private int sales;
    private int numDaysSales;

    public Notification(Product product, int numDaysSales) {
        this.product = product;
        this.numDaysSales = numDaysSales;
    }

    public Product getProduct() {
        return product;
    }

    public double getSalesPerDay() {
        return (double) sales / numDaysSales;
    }

    public void addSales(int numSales) {
        sales += numSales;
    }

    public int getNumDaysRemainingStock() {
        return (int) Math.ceil(product.getAmount() / getSalesPerDay());
    }

    public int getMinDeliveryTime() {
        return product.getSuppliers().stream().mapToInt(s -> s.getDeliveryTimeDays()).min().orElse(-1);
    }
}
