package com.Stocker.dto;

import java.util.Date;

public class CreateProductDTO {
    private Long barcode;
    private String name;
    private Double purchasePrice;
    private Double sellingPrice;
    private int amount;
    private Date expiryDate;
    
    public CreateProductDTO(Long barcode, String name, Double purchasePrice, Double sellingPrice, int amount,
                            Date expiryDate) {
        this.barcode = barcode;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public CreateProductDTO(Long barcode, String name, Double purchasePrice, Double sellingPrice, int amount) {
        this.barcode = barcode;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.amount = amount;
        this.expiryDate = null;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
