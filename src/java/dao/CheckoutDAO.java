package dao;

import entity.Checkout;
import entity.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class CheckoutDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = Logger.getLogger(CheckoutDAO.class);

    public void persist(Checkout object) {
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
    
    
    public List<Checkout> findAllCheckouts(User user){
         EntityManager em = emf.createEntityManager();
        ((JpaEntityManager) em.getDelegate()).getServerSession().getIdentityMapAccessor().invalidateAll();
        List<Checkout> result = null;
        try {
            String jpql = "SELECT c FROM Checkout c "
                    + "WHERE c.user = :user "
                    + "ORDER BY c.createAt DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("user", user);

            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }

     public List<Checkout> findAllCheckouts(User user, Date from, Date to){
         EntityManager em = emf.createEntityManager();
        ((JpaEntityManager) em.getDelegate()).getServerSession().getIdentityMapAccessor().invalidateAll();
        List<Checkout> result = null;
        try {
            String jpql = "SELECT c FROM Checkout c "
                    + "WHERE c.user = :user "
                    + "AND c.createAt BETWEEN :from AND :to "
                    + "ORDER BY c.createAt DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("user", user);
            query.setParameter("from", from);
            query.setParameter("to", to);

            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }
    
    public Checkout findTheLatest(User user) {
        EntityManager em = emf.createEntityManager();
        Checkout result = null;
        try {
            String jpql = "SELECT c FROM Checkout c "
                    + "WHERE c.user = :user "
                    + "ORDER BY c.createAt DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("user", user);
            result = (Checkout) query.getResultList().get(0);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }

    public Checkout findCheckouts() {
        EntityManager em = emf.createEntityManager();
        Checkout result = null;
        try {
            Query query = em.createNamedQuery("Checkout.findAll");
            result = (Checkout) query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return result;
    }

}
