package com.Stocker.repository;

import com.Stocker.entity.User;
import com.Stocker.util.HibernateUtil;

import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {

    public Long save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

            return user.getId();
        } 
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            
            return null;
        }
    }

    public User get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        } 
        catch (jakarta.persistence.NoResultException e) {
            return null;
        }
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

    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
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
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                System.out.println("User is deleted");
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
