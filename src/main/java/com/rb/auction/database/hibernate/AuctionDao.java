package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceAuctionDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuctionDao implements InterfaceAuctionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public int add(Auction auction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int auctionId = 0;

        try {
            transaction = session.beginTransaction();
            auctionId = (int) session.save(auction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return auctionId;
    }


    @Override
    public Optional<Auction> getById(int id) {
        Session session = this.sessionFactory.openSession();

        Query<Auction> query = session.createQuery("FROM com.rb.auction.model.Auction WHERE id = :id");
        query.setParameter("id", id);

        try {
            Auction auction = query.getSingleResult();
            return Optional.of(auction);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Auction auction) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(auction);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public int addBid(AuctionBet auctionBet) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        int id = 0;

        try {
            transaction = session.beginTransaction();
            id = (int) session.save(auctionBet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return id;
    }

    @Override
    public List<Auction> getAll() {
        Session session = this.sessionFactory.openSession();

        Query<Auction> query = session.createQuery("FROM com.rb.auction.model.Auction");

        try {
            List<Auction> auctions = query.getResultList();
            return auctions;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Auction> getByName(String searchKey) {
        Session session = this.sessionFactory.openSession();

        Query<Auction> query = session.createQuery("FROM com.rb.auction.model.Product WHERE title LIKE :searchKey");
        query.setParameter("searchKey", searchKey);

        List<Auction> auctions = new ArrayList<>();

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return auctions;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<AuctionBet> getBidById(int id) {
        Session session = this.sessionFactory.openSession();

        Query<AuctionBet> query = session.createQuery("FROM com.rb.auction.model.AuctionBid WHERE id = :id");
        query.setParameter("id", id);

        try {
            AuctionBet auctionBet = query.getSingleResult();
            return Optional.of(auctionBet);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }


}
