package util;

/**
 *
 * @author HOME
 */
public class URLConstants {

    //------SECTION Page------//
    public static final String LOGIN_PAGE = "login.jsp";
    /*authenticated user*/
    public static final String SEARCH_BOOK_PAGE = "search.jsp";
    public static final String CART_PAGE = "cart.jsp";
    /*admin priviledge*/
    public static final String SIGNUP_PAGE = "signup.jsp";
    public static final String UPDATE_BOOK_PAGE = "update-book.jsp";
    public static final String ADD_NEW_BOOK_PAGE = "add-new-book.jsp";
    //------SECTION Request------//
    public static final String LOGIN_REQUEST = "login";
    public static final String LOG_OUT = "logout";

    /*authenticated user*/
    public static final String SEARCH_BOOK_REQUEST = "search-book";
    public static final String CART_REQUEST = "cart";
    public static final String ADD_TO_CART_REQUEST = "add-to-cart";
    public static final String INCREASE_CART_ITEM_REQUEST = "increase-cart-item";
    public static final String DECREASE_CART_ITEM_REQUEST = "decrease-cart-item";
    public static final String REMOVE_CART_ITEM_REQUEST = "remove-cart-item";
    public static final String CHECKOUT_REQUEST = "checkout";
    /*admin priviledge*/
    public static final String SIGNUP_REQUEST = "signup";
    public static final String CHANGE_BOOK_STATUS_REQUEST = "change-book-status";
    public static final String UPDATE_BOOK_REQUEST = "update-book";
    public static final String ADD_NEW_BOOK_REQUEST = "add-new-book";
}
