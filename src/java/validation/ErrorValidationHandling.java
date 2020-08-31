package validation;

import java.io.Serializable;

/**
 *
 * @author ThongLV
 */
public class ErrorValidationHandling implements Serializable {

    private String emailError;
    private String passwordError;
    private String phoneError;
    private String nameError;
    private String addressError;
    private String duplicateEmailError;
    
    private String titleError;
    private String authorError;
    private String descriptionError;
    private String priceError;
    private String quantityError;

    public ErrorValidationHandling() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getDuplicateEmailError() {
        return duplicateEmailError;
    }

    public void setDuplicateEmailError(String duplicateEmailError) {
        this.duplicateEmailError = duplicateEmailError;
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getAuthorError() {
        return authorError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

   
}
