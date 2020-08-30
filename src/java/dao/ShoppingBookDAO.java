package dao;

import entity.ShoppingBook;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author ThongLV
 */
public class ShoppingBookDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = Logger.getLogger(ShoppingBookDAO.class);

    public void persist(ShoppingBook object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public ShoppingBook findOne(int id) {
        EntityManager em = emf.createEntityManager();
        ShoppingBook result = null;
        try {
            Query query = em.createNamedQuery("ShoppingBook.findById");
            query.setParameter("id", id);
            result = (ShoppingBook) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }

        return result;
    }

    public void increaseDecreaseCartItem(int shoppingBookId, int number) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("ShoppingBook.findById");
            query.setParameter("id", shoppingBookId);
            ShoppingBook result = (ShoppingBook) query.getSingleResult();
            if (number < 0 && result.getQuantity() == 1) {
                em.remove(result);
            } else {
                result.setQuantity(result.getQuantity() + number);
                em.merge(result);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void removeCartItem(int shoppingBookId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("ShoppingBook.findById");
            query.setParameter("id", shoppingBookId);
            ShoppingBook result = (ShoppingBook) query.getSingleResult();
            em.remove(result);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean isBookExistingInTheCart(int shoppingId, int bookId) {
        EntityManager em = emf.createEntityManager();
        boolean result = false;
        try {
            String jpql = "SELECT sb FROM ShoppingBook sb "
                    + "WHERE sb.shopping.id = :shoppingId "
                    + "AND sb.book.id = :bookId";
            Query query = em.createQuery(jpql);
            query.setParameter("shoppingId", shoppingId);
            query.setParameter("bookId", bookId);
            result = (ShoppingBook) query.getResultList().get(0) != null;
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }
}
