package controller.book;

import dao.BookDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HOME
 */
@WebServlet(name = "ChangeUserStatusServlet", urlPatterns = {"/change-book-status"})
public class ChangeBookStatusServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ChangeBookStatusServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Get Request");
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            BookDAO bookDAO = new BookDAO();
            bookDAO.changeBookStatus(Integer.parseInt(id));
        }
        String url = request.getHeader("referer");
        response.sendRedirect(url);
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
