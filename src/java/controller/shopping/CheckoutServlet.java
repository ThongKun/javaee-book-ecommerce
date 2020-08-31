package controller.shopping;

import dao.BookDAO;
import dao.CheckoutBookDAO;
import dao.CheckoutDAO;
import dao.DiscountDAO;
import dao.ShoppingBookDAO;
import dao.ShoppingDAO;
import entity.Checkout;
import entity.CheckoutBook;
import entity.Discount;
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
 * @author ThongLV
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CheckoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Get Request");
        //store checkout , checkout_book
        User user = (User) request.getSession().getAttribute("userinfo");
        Discount discount = (Discount) request.getSession().getAttribute("DISCOUNT");

        Checkout checkout = new Checkout();
        checkout.setUser(user);
        if (discount != null) {
            checkout.setDiscount(discount);
        }

        CheckoutDAO checkoutDAO = new CheckoutDAO();
        checkoutDAO.persist(checkout);

        CheckoutBookDAO checkoutBookDAO = new CheckoutBookDAO();

        ShoppingDAO shoppingDAO = new ShoppingDAO();
        Shopping currentShopping = shoppingDAO.findShopping(user);

        boolean importantCheck = true;
        for (ShoppingBook item : currentShopping.getShoppingBookList()) {
            ShoppingBookDAO sbDAO = new ShoppingBookDAO();
            if (item.getBook().getQuantity() == 0) {
                importantCheck = false;
                sbDAO.removeCartItem(item.getId());
            } else if (item.getBook().getQuantity() < item.getQuantity()) {
                importantCheck = false;
                item.setQuantity(item.getBook().getQuantity());
                sbDAO.update(item);
            }
        }

        if (!importantCheck) {
            request.setAttribute("IMPORTANT_ERROR", "We apologize that there the books in your cart that are run out of, and have to update the maximum amounts of books that you can buy.");

            Shopping userShoping = shoppingDAO.findShopping(user);

            request.setAttribute("SHOPPING", userShoping);

            RequestDispatcher rd = request.getRequestDispatcher(URLConstants.CART_PAGE);
            rd.forward(request, response);
        } else {
            for (ShoppingBook item : currentShopping.getShoppingBookList()) {
                CheckoutBook oneCheckoutBook = new CheckoutBook();
                oneCheckoutBook.setCheckout(checkout);
                oneCheckoutBook.setBook(item.getBook());
                oneCheckoutBook.setPrice(item.getBook().getPrice());
                oneCheckoutBook.setQuantity(item.getQuantity());

                checkoutBookDAO.persist(oneCheckoutBook);
                BookDAO bookDAO = new BookDAO();

                item.getBook().setQuantity(item.getBook().getQuantity() - item.getQuantity());
                bookDAO.update(item.getBook());
            }

            // shopping -> set false : delete shopping
            DiscountDAO discountDAO = new DiscountDAO();
            discountDAO.setDiscountUsed(discount);

            shoppingDAO.deactivateShopping(currentShopping);

            request.getSession().removeAttribute("DISCOUNT");
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
