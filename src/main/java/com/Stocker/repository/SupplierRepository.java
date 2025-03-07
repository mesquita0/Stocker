package com.Stocker.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;
import com.Stocker.util.HibernateUtil;

public class SupplierRepository extends BaseRepository<Supplier, Long> {

    public SupplierRepository() {
        super(Supplier.class);
    }

    public List<Supplier> getAll() {
        return getAll(null, null);
    }

    public List<Supplier> getAll(Product product) {
        return getAll(product, null);
    }

    public List<Supplier> getAll(String name) {
        return getAll(null, name);
    }

    public List<Supplier> getAll(Product product, String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder sb = new StringBuilder();
            sb.append("FROM Supplier s ");

            if (product != null) sb.append("JOIN s.products p WHERE p.product.id=:p_id");
            if (name != null) {
                if (product != null) sb.append(" AND ");
                else sb.append("WHERE ");

                sb.append("s.name LIKE :name");
            }
            
            SelectionQuery<Supplier> query = session.createSelectionQuery(
                sb.toString(), 
                Supplier.class
            );

            if (name != null) query.setParameter("name", '%' + name + '%');
            if (product != null) query.setParameter("p_id", product.getId());

            List<Supplier> suppliers = query.list();

            return suppliers;
        }
    }
}
