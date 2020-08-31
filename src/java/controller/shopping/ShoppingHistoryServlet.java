package controller.shopping;

import dao.CheckoutDAO;
import entity.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import util.RegexConstants;
import util.URLConstants;

/**
 *
 * @author ThongLV
 */
@WebServlet(name = "ShoppingHistoryServlet", urlPatterns = {"/shopping-history"})
public class ShoppingHistoryServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ShoppingHistoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Get Request");

        String from = request.getParameter("from");
        String to = request.getParameter("to");

        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = (from == null || !from.matches(RegexConstants.DATE_PATTERN)) ? new SimpleDateFormat("yyyy-MM-dd").parse("30-08-2000") : new SimpleDateFormat("yyyy-MM-dd").parse(from);
            dateTo = (to == null || !to.matches(RegexConstants.DATE_PATTERN)) ? new Date() : new SimpleDateFormat("yyyy-MM-dd").parse(to);
        } catch (ParseException ex) {
            LOGGER.error("exception : " + ex);
        }

        User user = (User) request.getSession().getAttribute("userinfo");

        CheckoutDAO checkoutDAO = new CheckoutDAO();

        request.setAttribute("CHECKOUTS", checkoutDAO.findAllCheckouts(user, dateFrom, dateTo));

        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.SHOPPING_HISTORY_PAGE);
        rd.forward(request, response);
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
