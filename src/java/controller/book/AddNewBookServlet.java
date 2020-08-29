package controller.book;

import dao.BookDAO;
import dao.CategoryDAO;
import entity.Book;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import util.URLConstants;
import validation.BookValidator;
import validation.ErrorValidationHandling;

/**
 *
 * @author HOME
 */
@WebServlet(name = "AddNewBookServlet", urlPatterns = {"/add-new-book"})
public class AddNewBookServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddNewBookServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Get Request");
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("CATEGORIES", categoryDAO.findCategories());

        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.ADD_NEW_BOOK_PAGE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Post Request");
        String title = null;
        String author = null;
        String description = null;
        String quantity = null;
        String price = null;
        String categoryId = null;
        String filename = null;

        CategoryDAO categoryDAO = new CategoryDAO();

        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (!isMultiPart) {

        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = (List) upload.parseRequest(new ServletRequestContext(request));
            } catch (FileUploadException e) {
                LOGGER.error(e);
            }
            Iterator iter = items.iterator();
            Hashtable params = new Hashtable();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    params.put(item.getFieldName(), item.getString());
                } else {
                    try {
                        String itemName = item.getName();
                        if (itemName.isEmpty()) {
                            continue;
                        }
                        filename = itemName;
                        String realPath = getServletContext().getInitParameter("upload.location") + "\\images\\" + filename;
                        File savedFile = new File(realPath);
                        item.write(savedFile);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }//end while

            boolean pass = true; //check if validation is ok
            ErrorValidationHandling errors = new ErrorValidationHandling();

            title = (String) params.get("title");
            author = (String) params.get("author");
            description = (String) params.get("description");
            System.out.println(description);
            quantity = (String) params.get("quantity");
            quantity = (quantity == null || quantity.isEmpty()) ? "0" : quantity;
            price = (String) params.get("price");
            price = (price == null || price.isEmpty()) ? "0" : price;
            categoryId = (String) params.get("categoryId");

            if (!BookValidator.validateTitle(title)) {
                pass = false;
                errors.setTitleError("Title must be 5 - 255 characters in length!");
            }
            if (!BookValidator.validateAuthor(author)) {
                pass = false;
                errors.setAuthorError("Author must be 5 - 55 characters in length!");
            }
            if (!BookValidator.validateDescription(description)) {
                pass = false;
                errors.setDescriptionError("Description must be greater than 5 characters in length!");
            }
            if (!BookValidator.validateQuantity(Integer.parseInt(quantity))) {
                pass = false;
                errors.setQuantityError("Quantity must be >= 1");
            }
            if (!BookValidator.validatePrice(Integer.parseInt(price))) {
                pass = false;
                errors.setPriceError("Price must be positive");
            }

            if (!pass) {
                request.setAttribute("errors", errors);
            } else {
                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setDescription(description);
                book.setCategory(categoryDAO.findCategory(Integer.parseInt(categoryId)));
                book.setQuantity(Integer.parseInt(quantity));
                book.setPrice(Integer.parseInt(price));
                book.setImg(filename);
                BookDAO bookDAO = new BookDAO();
                bookDAO.persist(book);
                request.setAttribute("success", "ADDED BOOK!!!🎉🎉🎉");
            }

        }

        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("description", description);
        request.setAttribute("price", price);
        request.setAttribute("quantity", quantity);
        request.setAttribute("categoryId", categoryId);

        request.setAttribute("CATEGORIES", categoryDAO.findCategories());
        RequestDispatcher rd = request.getRequestDispatcher(URLConstants.ADD_NEW_BOOK_PAGE);
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
