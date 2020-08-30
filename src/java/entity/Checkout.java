package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ThongLV
 */
@Entity
@Table(catalog = "lab02", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Checkout.findAll", query = "SELECT c FROM Checkout c"),
    @NamedQuery(name = "Checkout.findById", query = "SELECT c FROM Checkout c WHERE c.id = :id"),
    @NamedQuery(name = "Checkout.findByCreateAt", query = "SELECT c FROM Checkout c WHERE c.createAt = :createAt"),
    @NamedQuery(name = "Checkout.findByUpdateAt", query = "SELECT c FROM Checkout c WHERE c.updateAt = :updateAt")})
public class Checkout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checkout")
    private List<CheckoutBook> checkoutBookList;
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    @ManyToOne
    private Discount discount;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    public Checkout() {
    }

    public Checkout(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<CheckoutBook> getCheckoutBookList() {
        return checkoutBookList;
    }

    public void setCheckoutBookList(List<CheckoutBook> checkoutBookList) {
        this.checkoutBookList = checkoutBookList;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checkout)) {
            return false;
        }
        Checkout other = (Checkout) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.test.Checkout[ id=" + id + " ]";
    }

    @PrePersist
    void createAt() {
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    @PreUpdate
    void updateAt() {
        this.updateAt = new Date();
    }
}
