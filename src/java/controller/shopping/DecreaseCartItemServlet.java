package controller.shopping;

import dao.ShoppingBookDAO;
import entity.ShoppingBook;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import util.URLConstants;

/**
 *
 * @author ThongLV
 */
@WebServlet(name = "DecreaseCartItemServlet", urlPatterns = {"/decrease-cart-item"})
public class DecreaseCartItemServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(DecreaseCartItemServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Get Request");
        String shoppingBookId = request.getParameter("shoppingBookId");

        ShoppingBookDAO shoppingBookDAO = new ShoppingBookDAO();

        ShoppingBook shoppingBook = shoppingBookDAO.findOne(Integer.parseInt(shoppingBookId));
        if (!shoppingBookId.matches("\\d{1,}") || shoppingBook == null) {
            response.sendRedirect(URLConstants.SEARCH_BOOK_REQUEST);
        } else {
            if (!shoppingBook.getBook().getStatus() || shoppingBook.getBook().getQuantity() < 1) {
                shoppingBookDAO.removeCartItem(Integer.parseInt(shoppingBookId));
            } else {
                shoppingBookDAO.increaseDecreaseCartItem(Integer.parseInt(shoppingBookId), -1);
            }
            response.sendRedirect(URLConstants.CART_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        LOGGER.info("INITILIZED");
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroyed");
    }
}
