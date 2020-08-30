/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import entity.Category;
import java.io.Serializable;
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
public class BookDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02-officialPU");
    static final Logger LOGGER = Logger.getLogger(BookDAO.class);

    public BookDAO() {
        LOGGER.info("Constructed");
    }

    public void persist(Book object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception Caught: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Book findBookById(int bookId) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Book.findById");
            query.setParameter("id", bookId);

            return (Book) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            em.close();
        }
        return null;
    }

    /**
     * *
     * Function Desc: Ban/Activate Book
     *
     * @param bookId
     *
     */
    public void changeBookStatus(int bookId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT b FROM Book b WHERE b.id = :bookId";
            Query query = em.createQuery(jpql);
            query.setParameter("bookId", bookId);

            Book book = (Book) query.getSingleResult();
            if (book != null) {
                em.getTransaction().begin();
                book.setStatus(!book.getStatus());
                em.merge(book);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * *
     * Function Desc: Search Book Based on Name, maximum money, book category
     *
     * @param searchValue
     * @param miniMoney
     * @param categoryId
     *
     * @return Book List
     */
    public List<Book> findBooks(String searchValue, int miniMoney, int categoryId) {
        EntityManager em = emf.createEntityManager();
        ((JpaEntityManager) em.getDelegate()).getServerSession().getIdentityMapAccessor().invalidateAll();
        List<Book> result = null;

        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            String jpql = "SELECT b from Book b "
                    + "WHERE b.quantity > 0 "
                    + "AND b.title LIKE :searchValue "
                    + "AND b.price >= :miniMoney ";
            Category category = categoryDAO.findCategory(categoryId);
            LOGGER.info(category);
            if (category != null) {
                jpql += "AND b.category = :category";
            }

            Query query = em.createQuery(jpql);
            query.setParameter("searchValue", "%" + searchValue + "%");
            query.setParameter("miniMoney", miniMoney);
            if (category != null) {
                query.setParameter("category", category);
            }

            result = query.getResultList();
            LOGGER.info("result: " + result);
        } catch (Exception e) {
            LOGGER.error("Exception Caught: " + e);
        }
        return result;
    }

    public List<Book> findBooks() {
        EntityManager em = emf.createEntityManager();
        List<Book> result = null;
        try {
            Query query = em.createNamedQuery("Book.findAll");
            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception Caught: " + e);
        }
        return result;
    }

    public void update(Book newBook) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT b FROM Book b WHERE b.id = :id";
            Query query = em.createQuery(jpql);
            query.setParameter("id", newBook.getId());

            Book book = (Book) query.getSingleResult();

            em.getTransaction().begin();
            
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setDescription(newBook.getDescription());
            book.setCategory(newBook.getCategory());
            book.setQuantity(newBook.getQuantity());
            book.setPrice(newBook.getPrice());
            book.setImg(newBook.getImg());
            em.merge(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
             em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
