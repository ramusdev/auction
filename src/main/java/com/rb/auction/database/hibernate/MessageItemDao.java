package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceMessageItemDao;
import com.rb.auction.model.MessageItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageItemDao implements InterfaceMessageItemDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(MessageItem messageItem) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(messageItem);
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
