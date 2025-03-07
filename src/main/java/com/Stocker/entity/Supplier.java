package com.Stocker.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cnpj;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierProduct> products;


    public Supplier() {}

    public Supplier(Long id, String name, String cnpj, List<SupplierProduct> products) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.products = products;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }   

    public List<SupplierProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SupplierProduct> products) {
        this.products = products;
    }
}
