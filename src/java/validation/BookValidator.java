package validation;

import util.RegexConstants;

/**
 *
 * @author ThongLV
 */
public class BookValidator {

    public static boolean validateTitle(String title) {
        return title.matches(RegexConstants.TITLE_PATTERN);
    }

    public static boolean validateDescription(String description) {
        return description.matches(RegexConstants.DESCRIPTION_PATTERN);
    }

    public static boolean validateQuantity(int quantity) {
        return quantity >= 1;
    }

    public static boolean validatePrice(String price) {
        return price.matches("^\\d*\\.\\d+|\\d+\\.\\d*$");
    }

    public static boolean validateAuthor(String author) {
        return author.matches(RegexConstants.AUTHOR_PATTERN);
    }
}
