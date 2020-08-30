package util;

/**
 *
 * @author ThongLV
 */
public class RegexConstants {
    public static final String EMAIL_PATTERN = "^[a-z][a-z0-9_\\.]{2,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    public static final String PASSWORD_PATTERN = "[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]{6,30}";
    public static final String PHONE_PATTERN = "^0[1-9]{1}[0-9]{8,9}";
    public static final String NAME_PATTERN = "[a-zA-Z \\.]{1,30}";
    public static final String ADDRESS_PATTERN = "[a-zA-Z0-9 \\.]{0,50}";
    
    public static final String POSITIVE_INTEGER_PATTERN  = "\\d+";
    
    public static final String TITLE_PATTERN = ".{5,255}";
    public static final String DESCRIPTION_PATTERN = "[\\.\\s\\S]{5,}";
    public static final String AUTHOR_PATTERN = TITLE_PATTERN;
    
}
