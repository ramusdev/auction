package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceMessageChatDao;
import com.rb.auction.model.MessageChat;
import com.rb.auction.model.User;
import com.rb.auction.service.InterfaceUserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageChatDao implements InterfaceMessageChatDao {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    InterfaceUserService userService;

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

    /*
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
    */

    @Override
    public Optional<MessageChat> getByProductAndUser(int productId, List<Integer> userId) {
        Session session = sessionFactory.openSession();

        // productId = 100;
        // List<Integer> userId = Arrays.asList(2, 5);

        try {
            MessageChat query = (MessageChat) session.createNativeQuery("" +
                            "SELECT *\n" +
                            "FROM chats\n" +
                            "WHERE chats.participant_id = (\n" +
                            "    SELECT participant_user.participant_id\n" +
                            "    FROM participant_user\n" +
                            "    LEFT JOIN users ON users.id = participant_user.user_id\n" +
                            "    WHERE participant_user.user_id IN (:userId)\n" +
                            "    GROUP BY participant_user.participant_id\n" +
                            "    HAVING COUNT(participant_user.participant_id) = :userSize\n" +
                            "    AND chats.product_id = :productId\n" +
                            ")\n")
                    .setParameter("productId", productId)
                    .setParameter("userId", userId)
                    .setParameter("userSize", userId.size())
                    .addEntity(MessageChat.class)
                    .getSingleResult();

            return Optional.of(query);
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }

        // System.out.println(query.get(0).getProduct().getText());
        // System.out.println(query.getProduct().getText());

        /*
        for (Object[] item : query) {
            int id = (int) item[0];
            System.out.println("From query: " + id);
        }
        */

        /*
        try {
            List<MessageChat> messageChat = query.getResultList();
            System.out.println(messageChat.get(0).getId());
            return Optional.empty();
        } catch (NoResultException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
        */

    }
}
