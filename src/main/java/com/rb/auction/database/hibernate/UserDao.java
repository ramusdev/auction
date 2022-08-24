package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceUserDao;
import com.rb.auction.model.User;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class UserDao implements InterfaceUserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {

        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("----------------------------------------");
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

    }

    @Override
    public Optional<User> getUserById(int id) {
        Session session  = this.sessionFactory.openSession();

        Query<User> query = session.createQuery("FROM com.rb.auction.model.User WHERE id = :id");
        query.setParameter("id", id);

        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        System.out.println("--------------------------------------------");
        System.out.println("get user by login");

        Session session = this.sessionFactory.openSession();

        Query<User> query = session.createQuery("FROM com.rb.auction.model.User WHERE login = :login");
        query.setParameter("login", login);

        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

}
