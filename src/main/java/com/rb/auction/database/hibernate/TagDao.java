package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceTagDao;
import com.rb.auction.model.Tag;
import com.rb.auction.session.SessionObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao implements InterfaceTagDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Tag tag) {
        Session session = this.sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
