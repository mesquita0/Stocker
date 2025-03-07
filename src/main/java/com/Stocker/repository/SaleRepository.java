package com.Stocker.repository;

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

    public List<Sale> getAll(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           SelectionQuery<Sale> query = session.createSelectionQuery(
                "FROM Sale s WHERE s.product.user.id=:u_id", 
                Sale.class
            );

            query.setParameter("u_id", user.getId());

            List<Sale> sales = query.list();

            return sales;
        }
    }
}
