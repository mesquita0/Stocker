package com.Stocker.repository;

import com.Stocker.entity.User;
import com.Stocker.util.HibernateUtil;

import jakarta.persistence.Query;

import org.hibernate.Session;

public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository() {
        super(User.class);
    }

    public User get(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE email=:email", User.class);
            query.setParameter("email", email);

            User user = (User) query.getSingleResult();

            return user;
        } 
        catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}
