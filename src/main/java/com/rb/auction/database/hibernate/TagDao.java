package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceTagDao;
import com.rb.auction.model.Tag;
import com.rb.auction.session.SessionObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Tag> getAll() {
        Session session = this.sessionFactory.openSession();

        Query<Tag> query = session.createQuery("FROM com.rb.auction.model.Tag");

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Tag> getBySlug(String slug) {
        Session session = this.sessionFactory.openSession();

        Query<Tag> query = session.createQuery("FROM com.rb.auction.model.Tag WHERE slug = :slug");
        query.setParameter("slug", slug);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e){
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
