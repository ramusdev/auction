package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceMessageChatDao;
import com.rb.auction.model.MessageChat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class MessageChatDao implements InterfaceMessageChatDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<MessageChat> getById(int id) {
        Session session = sessionFactory.openSession();

        Query<MessageChat> query = session.createQuery("FROM com.rb.auction.model.MessageChat WHERE id = :id");
        query.setParameter("id", id);

        try {
            MessageChat messageChat = query.getSingleResult();
            return Optional.of(messageChat);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public MessageChat add(MessageChat messageChat) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            int id = (int) session.save(messageChat);
            transaction.commit();
            messageChat.setId(id);

            return messageChat;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public Optional<MessageChat> getByProductAndUser(int productId, int userId) {
        Session session = sessionFactory.openSession();

        Query<MessageChat> query = session.createQuery("FROM com.rb.auction.model.MessageChat WHERE product.id = :productId AND user.id = :userId");
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);

        try {
            MessageChat messageChat = query.getSingleResult();
            return Optional.of(messageChat);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
