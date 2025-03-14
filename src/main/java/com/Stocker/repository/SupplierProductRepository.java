package com.Stocker.repository;

import com.Stocker.entity.SupplierProduct;

public class SupplierProductRepository extends BaseRepository<SupplierProduct, SupplierProduct.PK> {
    
    public SupplierProductRepository() {
        super(SupplierProduct.class);
    }   

    @Override
    public SupplierProduct save(SupplierProduct sp) {
        // Atualiza ids
        sp.setProduct(sp.getProduct());
        sp.setSupplier(sp.getSupplier());

        return super.update(sp);
    }
}
