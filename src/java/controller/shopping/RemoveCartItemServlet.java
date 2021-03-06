package controller.shopping;

import dao.ShoppingBookDAO;
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
@WebServlet(name = "RemoveCartItemServlet", urlPatterns = {"/remove-cart-item"})
public class RemoveCartItemServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(RemoveCartItemServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Get Request");
        String shoppingBookId = request.getParameter("shoppingBookId");

        ShoppingBookDAO shoppingBookDAO = new ShoppingBookDAO();

        if (!shoppingBookId.matches("\\d{1,}") || shoppingBookDAO.findOne(Integer.parseInt(shoppingBookId)) == null) {
            response.sendRedirect(URLConstants.SEARCH_BOOK_REQUEST);
        } else {
            shoppingBookDAO.removeCartItem(Integer.parseInt(shoppingBookId));
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
