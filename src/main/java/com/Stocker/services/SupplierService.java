package com.Stocker.services;

import java.util.List;

import com.Stocker.entity.Supplier;
import com.Stocker.entity.User;
import com.Stocker.repository.SupplierRepository;

public class SupplierService {
    private SupplierRepository supplierRepository;
    private User user;

    public SupplierService(User user) {
        supplierRepository = new SupplierRepository();
        this.user = user;
    }

    public Supplier createSupplier(String name, String cnpj) {
        Supplier newSupplier = new Supplier(null, name, cnpj, null);

        return supplierRepository.save(newSupplier);
    }

    public List<Supplier> listSuppliers(String name) {
        return supplierRepository.getAll(user, name);
    }

    public void updateSupplier(Supplier supplier) {
        supplierRepository.update(supplier);
    }

    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier.getId());
    }
}
