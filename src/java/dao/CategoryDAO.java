package dao;

import entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author ThongLV
 */
public class CategoryDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    public List<Category> findCategories() {
        EntityManager em = emf.createEntityManager();
        List<Category> result = null;
        try {
            Query query = em.createNamedQuery("Category.findAll");
            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.error("exception caught " + e);
        } finally {
            em.close();
        }
        return result;
    }
    
    public Category findCategory(int categoryId) {
        EntityManager em = emf.createEntityManager();
        Category result = null;
        try {
            Query query = em.createNamedQuery("Category.findById").setParameter("id", categoryId);
            result = (Category) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error("exception caught " + e);
        } finally {
            em.close();
        }
        return result;
    }
}
