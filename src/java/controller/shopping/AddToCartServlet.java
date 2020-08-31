package controller.shopping;

import dao.BookDAO;
import dao.ShoppingBookDAO;
import dao.ShoppingDAO;
import entity.Book;
import entity.Shopping;
import entity.ShoppingBook;
import entity.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import util.URLConstants;

/**
 *
 * @author HOME
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddToCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go Get Request");
        String bookId = request.getParameter("bookId");
        if (!bookId.matches("\\d{1,}")) {
            response.sendRedirect(URLConstants.SEARCH_BOOK_REQUEST);
        } else {
            User user = (User) request.getSession(false).getAttribute("userinfo");
            ShoppingDAO shoppingDAO = new ShoppingDAO();
            //check lai
            Shopping shopping = shoppingDAO.findShopping(user);
            if (shopping == null) { //not existing before
                Shopping newShopping = new Shopping();
                newShopping.setUser(user);
                shoppingDAO.persist(newShopping);
            }
            shopping = shoppingDAO.findShopping(user);
            //check lai
            ShoppingBookDAO sbDAO = new ShoppingBookDAO();

            BookDAO bookDAO = new BookDAO();
            Book selectedBook = bookDAO.findBookById(Integer.parseInt(bookId));
            if (!sbDAO.isBookExistingInTheCart(shopping.getId(), Integer.parseInt(bookId)) && selectedBook.getStatus() && selectedBook.getQuantity() >= 1) {
                System.out.println("SELECTED BOOK : " + selectedBook.getQuantity());
                ShoppingBook shoppingBook = new ShoppingBook();
                shoppingBook.setShopping(shopping);
                shoppingBook.setBook(selectedBook);

                sbDAO.persist(shoppingBook);
            }

            RequestDispatcher rd = request.getRequestDispatcher(URLConstants.SEARCH_BOOK_REQUEST);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go Post Request");
        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.SEARCH_BOOK_PAGE);
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    public void init() throws ServletException {
        LOGGER.info("INITILIZED");
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroyed");
    }
}
