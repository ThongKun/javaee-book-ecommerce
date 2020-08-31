package controller.shopping;

import dao.DiscountDAO;
import entity.Discount;
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
@WebServlet(name = "GenerateDiscountCodeServlet", urlPatterns = {"/generate-discount-code"})
public class GenerateDiscountCodeServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(GenerateDiscountCodeServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Get Request");

        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.GENERATE_DISCOUNT_CODE_PAGE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Go To Post Request");

        String offer = request.getParameter("offer");
        String code = request.getParameter("code");

        boolean pass = true;
        if (!offer.matches("\\d{1,100}")) {
            pass = false;
            request.setAttribute("offerError", "Offer must be from 1 to 100 in terms of number!");
        }

        if (code == null || code.isEmpty()) {
            pass = false;
            request.setAttribute("codeError", "code cannot be empty");
        }

        if (pass) {
            DiscountDAO discountDAO  = new DiscountDAO();
            Discount discount = new Discount();
            discount.setCode(code);
            discount.setOffer(Integer.parseInt(offer));
            discountDAO.persist(discount);
            
            request.setAttribute("success", "Generate Code Success!");
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.GENERATE_DISCOUNT_CODE_PAGE);
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
