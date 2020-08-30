package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ThongLV
 */
@Entity
@Table(name = "checkout_book", catalog = "lab02", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "CheckoutBook.findAll", query = "SELECT c FROM CheckoutBook c"),
    @NamedQuery(name = "CheckoutBook.findById", query = "SELECT c FROM CheckoutBook c WHERE c.id = :id"),
    @NamedQuery(name = "CheckoutBook.findByQuantity", query = "SELECT c FROM CheckoutBook c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "CheckoutBook.findByPrice", query = "SELECT c FROM CheckoutBook c WHERE c.price = :price")})
public class CheckoutBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "checkout_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Checkout checkout;

    public CheckoutBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.quantity;
        hash = 53 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 53 * hash + (this.book != null ? this.book.hashCode() : 0);
        hash = 53 * hash + (this.checkout != null ? this.checkout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CheckoutBook other = (CheckoutBook) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price))) {
            return false;
        }
        if (this.book != other.book && (this.book == null || !this.book.equals(other.book))) {
            return false;
        }
        if (this.checkout != other.checkout && (this.checkout == null || !this.checkout.equals(other.checkout))) {
            return false;
        }
        return true;
    }

}
