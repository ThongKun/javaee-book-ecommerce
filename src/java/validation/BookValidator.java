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

    public static boolean validatePrice(int price) {
        return price > 0;
    }

    public static boolean validateAuthor(String author) {
        return author.matches(RegexConstants.AUTHOR_PATTERN);
    }
}
