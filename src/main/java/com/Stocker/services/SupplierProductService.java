package com.Stocker.services;

import com.Stocker.dto.CreateSupplierProductDTO;
import com.Stocker.entity.SupplierProduct;
import com.Stocker.repository.SupplierProductRepository;

public class SupplierProductService {
    private SupplierProductRepository supplierProductRepository;

    public SupplierProductService() {
        supplierProductRepository = new SupplierProductRepository();
    }

    public SupplierProduct createSupplierProduct(CreateSupplierProductDTO sp) {
        SupplierProduct new_sp = new SupplierProduct(
            sp.getProduct(), 
            sp.getSupplier(), 
            sp.getDeliveryTime(), 
            sp.getPrice()
        );

        return supplierProductRepository.save(new_sp);
    }

    public void updateSupplierProduct(SupplierProduct supplier) {
        supplierProductRepository.update(supplier);
    }

    public void deleteSupplierProduct(SupplierProduct supplier) {
        supplierProductRepository.delete(supplier.getId());
    }
}
