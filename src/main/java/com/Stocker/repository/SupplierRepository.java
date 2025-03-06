package com.Stocker.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import com.Stocker.entity.Product;
import com.Stocker.entity.Supplier;
import com.Stocker.util.HibernateUtil;

public class SupplierRepository {

    public Long save(Supplier supplier) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(supplier);
            transaction.commit();

            return supplier.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return null;
        }
    }

    public Supplier get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Supplier.class, id);
        }
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

            if (product != null) sb.append("JOIN s.products p WHERE p.id=:p_id");
            if (name != null) {
                if (product != null) sb.append(" AND ");
                else sb.append("WHERE ");

                sb.append("s.name LIKE :name");
            }
            

            String s = sb.toString();
            SelectionQuery<Supplier> query = session.createSelectionQuery(
                s, 
                Supplier.class
            );

            if (name != null) query.setParameter("name", '%' + name + '%');
            if (product != null) query.setParameter("p_id", product.getId());

            List<Supplier> suppliers = query.list();

            return suppliers;
        }
    }

    public void update(Supplier supplier) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(supplier);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Supplier supplier = session.get(Supplier.class, id);
            if (supplier != null) {
                session.remove(supplier);
                System.out.println("Supplier is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
