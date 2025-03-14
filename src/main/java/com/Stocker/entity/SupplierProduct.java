package com.Stocker.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_product")
@IdClass(SupplierProduct.PK.class)
public class SupplierProduct {

    public static class PK implements Serializable {
        protected Long productId;
        protected Long supplierId;

        public PK() {}

        public PK(Long productId, Long supplierId) {
            this.productId = productId;
            this.supplierId = supplierId;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof PK)) return false;
            PK otherPK = (PK) other;

            return (productId == otherPK.productId && supplierId == otherPK.supplierId);
        }
    }
    
    @Id
    @Column(name="product_id")
    private Long productId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Id
    @Column(name="supplier_id")
    private Long supplierId;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;

    @Column(name = "delivery_time")
    private Integer deliveryTimeDays;

    private Double price;
    
    
    public SupplierProduct() {}

    public SupplierProduct(Product product, Supplier supplier, Integer deliveryTimeDays, Double price) {
        this.productId = product.getId();
        this.product = product;
        this.supplierId = supplier.getId();
        this.supplier = supplier;
        this.deliveryTimeDays = deliveryTimeDays;
        this.price = price;
    }


    public PK getId() {
        return new PK(productId, supplierId);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.productId = product.getId();
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplierId = supplier.getId();
        this.supplier = supplier;
    }

    public Integer getDeliveryTimeDays() {
        return deliveryTimeDays;
    }

    public void setDeliveryTimeDays(Integer deliveryTimeDays) {
        this.deliveryTimeDays = deliveryTimeDays;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
