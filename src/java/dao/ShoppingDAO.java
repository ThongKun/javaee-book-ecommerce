package dao;

import entity.Shopping;
import entity.User;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.JpaEntityManager;

/**
 *
 * @author ThongLV
 */
public class ShoppingDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");

    static final Logger LOGGER = Logger.getLogger(ShoppingDAO.class);

    public void persist(Shopping newShopping) {
        EntityManager em = emf.createEntityManager();
        try {
            if (findShopping(newShopping.getUser()) == null) {
                em.getTransaction().begin();
                em.persist(newShopping);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Shopping findShopping(User user) {
        EntityManager em = emf.createEntityManager();
        ((JpaEntityManager) em.getDelegate()).getServerSession().getIdentityMapAccessor().invalidateAll();
        Shopping result = null;
        try {
            String jpql = "SELECT s FROM Shopping s "
                    + "WHERE s.user = :user "
                    + "AND s.status = true "
                    + "ORDER BY s.updateAt DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("user", user);

            result = (Shopping) query.getResultList().get(0);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }

    public void deactivateShopping(Shopping shopping) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            shopping.setStatus(false);
            em.merge(shopping);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
