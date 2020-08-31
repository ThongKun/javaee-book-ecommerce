package dao;

import entity.CheckoutBook;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

/**
 *
 * @author ThongLV
 */
public class CheckoutBookDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = Logger.getLogger(CheckoutBookDAO.class);

    public void persist(CheckoutBook object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("exception caught: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void update(CheckoutBook object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("exception caught: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
