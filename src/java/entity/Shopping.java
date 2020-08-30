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
    @NamedQuery(name = "Shopping.findAll", query = "SELECT s FROM Shopping s"),
    @NamedQuery(name = "Shopping.findById", query = "SELECT s FROM Shopping s WHERE s.id = :id"),
    @NamedQuery(name = "Shopping.findByCreateAt", query = "SELECT s FROM Shopping s WHERE s.createAt = :createAt"),
    @NamedQuery(name = "Shopping.findByUpdateAt", query = "SELECT s FROM Shopping s WHERE s.updateAt = :updateAt"),
    @NamedQuery(name = "Shopping.findByStatus", query = "SELECT s FROM Shopping s WHERE s.status = :status")})
public class Shopping implements Serializable {

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
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopping")
    private List<ShoppingBook> shoppingBookList;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    public Shopping() {
    }

    public Shopping(Integer id) {
        this.id = id;
    }

    public Shopping(Integer id, boolean status) {
        this.id = id;
        this.status = status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ShoppingBook> getShoppingBookList() {
        return shoppingBookList;
    }

    public void setShoppingBookList(List<ShoppingBook> shoppingBookList) {
        this.shoppingBookList = shoppingBookList;
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
        if (!(object instanceof Shopping)) {
            return false;
        }
        Shopping other = (Shopping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.test.Shopping[ id=" + id + " ]";
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
