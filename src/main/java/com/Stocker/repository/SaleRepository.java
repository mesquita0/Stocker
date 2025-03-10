package com.Stocker.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.Stocker.entity.Sale;
import com.Stocker.entity.User;
import com.Stocker.util.HibernateUtil;

public class SaleRepository extends BaseRepository<Sale, Long> {
    public SaleRepository() {
        super(Sale.class);
    }

    public List<Sale> getByDate(User user, Date fromDate, Date toDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SelectionQuery<Sale> query = session.createSelectionQuery(
                 "FROM Sale s WHERE s.product.user.id=:u_id AND s.date BETWEEN :fDate AND :tDate", 
                 Sale.class
             );
 
             query.setParameter("u_id",  user.getId());
             query.setParameter("fDate", fromDate);
             query.setParameter("tDate", toDate);
 
             List<Sale> sales = query.list();
 
             return sales;
         }
    }

    public List<Sale> getAll(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           SelectionQuery<Sale> query = session.createSelectionQuery(
                "FROM Sale s WHERE s.product.user.id=:u_id ORDER BY s.date DESC", 
                Sale.class
            );

            query.setParameter("u_id", user.getId());

            List<Sale> sales = query.list();

            return sales;
        }
    }
}
