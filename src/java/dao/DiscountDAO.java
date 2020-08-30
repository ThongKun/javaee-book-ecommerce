package dao;

import entity.Discount;
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
public class DiscountDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = org.apache.log4j.Logger.getLogger(DiscountDAO.class);

    public void persist(Object object) {
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

    public Discount findDiscount(String text) {
        EntityManager em = emf.createEntityManager();
        Discount result = null;
        try {
            String jpql = "SELECT d FROM Discount d "
                    + "WHERE d.code = :text "
                    + "AND d.isUsed = false";
            Query query = em.createQuery(jpql);
            query.setParameter("text", text);
            result = (Discount) query.getResultList().get(0);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }

    public void setDiscountUsed(Discount discount) {
         EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            discount.setIsUsed(true);
            em.merge(discount);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
