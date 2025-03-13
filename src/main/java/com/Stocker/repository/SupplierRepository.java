package com.Stocker.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;
import com.Stocker.entity.User;
import com.Stocker.util.HibernateUtil;

public class SupplierRepository extends BaseRepository<Supplier, Long> {

    public SupplierRepository() {
        super(Supplier.class);
    }

    public List<Supplier> getAll(User user) {
        return getAll(user, null, null);
    }

    public List<Supplier> getAll(Product product) {
        return getAll(null, product, null);
    }

    public List<Supplier> getAll(User user, String name) {
        return getAll(user, null, name);
    }

    public List<Supplier> getAll(Product product, String name) {
        return getAll(null, product, name);
    }

    private List<Supplier> getAll(User user, Product product, String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder sb = new StringBuilder();
            sb.append("FROM Supplier s LEFT JOIN s.products p WHERE ");

            if (user != null)    sb.append("s.user.id=:u_id AND ");
            if (product != null) sb.append("p.product.id=:p_id AND ");
            if (name != null)    sb.append("s.name LIKE :name AND ");

            SelectionQuery<Supplier> query = session.createSelectionQuery(
                sb.substring(0, sb.length() - 5), 
                Supplier.class
            );

            if (user != null)    query.setParameter("u_id", user.getId());
            if (name != null)    query.setParameter("name", '%' + name + '%');
            if (product != null) query.setParameter("p_id", product.getId());

            List<Supplier> suppliers = query.list();

            return suppliers;
        }
    }
}
