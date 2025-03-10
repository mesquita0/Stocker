package com.Stocker.repository;

import com.Stocker.entity.Product;
import com.Stocker.entity.User;
import com.Stocker.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class ProductRepository extends BaseRepository<Product, Long> {

    public ProductRepository() {
        super(Product.class);
    }
    
    public List<Product> getByBarcode(Long barcode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SelectionQuery<Product> query = session.createSelectionQuery("FROM Product WHERE barcode=:barcode", Product.class);
            query.setParameter("barcode", barcode);

            List<Product> products = query.list();

            return products;
        } 
        catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    public List<Product> getAll(User user) {
        return getAll(user, null);
    }

    public List<Product> getAll(User user, String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder sb = new StringBuilder();
            sb.append("FROM Product p JOIN p.user u WHERE u.id=:u_id ");

            if (name != null) sb.append("AND p.name LIKE :name");

            SelectionQuery<Product> query = session.createSelectionQuery(
                sb.toString(), 
                Product.class
            );

            query.setParameter("u_id", user.getId());
            if (name != null) query.setParameter("name", '%' + name + '%');

            List<Product> products = query.list();

            return products;
        }
    }
}
