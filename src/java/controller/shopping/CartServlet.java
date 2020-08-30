package controller.shopping;

import dao.DiscountDAO;
import dao.ShoppingDAO;
import entity.Discount;
import entity.Shopping;
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
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go Get Request");
        User user = (User)request.getSession(false).getAttribute("userinfo");
        ShoppingDAO shoppingDAO = new ShoppingDAO();
        Shopping userShoping = shoppingDAO.findShopping(user);
        
        request.setAttribute("SHOPPING", userShoping);
        
        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.CART_PAGE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go Post Request");
        //reset
        request.getSession().removeAttribute("DISCOUNT");
        
        String couponCode = request.getParameter("coupon-code");
        DiscountDAO discountDAO = new DiscountDAO();
        Discount discount = discountDAO.findDiscount(couponCode);
        if (discount != null) {
            request.getSession(true).setAttribute("DISCOUNT", discount);
        }
        
        response.sendRedirect(URLConstants.CART_REQUEST);
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
