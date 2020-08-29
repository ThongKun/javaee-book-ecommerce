package controller.book;

import dao.BookDAO;
import dao.CategoryDAO;
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
@WebServlet(name = "SearchBookServlet", urlPatterns = {"/search-book"})
public class SearchBookServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(SearchBookServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Get Request");
        String searchKey = request.getParameter("s"); //query parameter s: search
        String minimumMoney = request.getParameter("minimumMoney");
        String categoryId = request.getParameter("categoryId");
        
        searchKey = searchKey == null ? "" : searchKey;
        minimumMoney = (minimumMoney == null || minimumMoney.isEmpty() || Integer.parseInt(minimumMoney) < 1) ? "1" : minimumMoney;
        categoryId = (categoryId == null || categoryId.isEmpty() || Integer.parseInt(categoryId) < 1) ? "0" : categoryId;

        // Business Logic
        BookDAO bookDAO = new BookDAO();
        request.setAttribute("books", bookDAO.findBooks(searchKey, Integer.parseInt(minimumMoney), Integer.parseInt(categoryId)));
        
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("CATEGORIES", categoryDAO.findCategories());

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
