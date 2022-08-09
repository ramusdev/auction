package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceBetDao;
import com.rb.auction.model.AuctionBet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BetDao implements InterfaceBetDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(AuctionBet bet) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(bet);
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
