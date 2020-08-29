/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
    @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d"),
    @NamedQuery(name = "Discount.findById", query = "SELECT d FROM Discount d WHERE d.id = :id"),
    @NamedQuery(name = "Discount.findByOffer", query = "SELECT d FROM Discount d WHERE d.offer = :offer"),
    @NamedQuery(name = "Discount.findByIsUsed", query = "SELECT d FROM Discount d WHERE d.isUsed = :isUsed"),
    @NamedQuery(name = "Discount.findByStatus", query = "SELECT d FROM Discount d WHERE d.status = :status"),
    @NamedQuery(name = "Discount.findByCreateAt", query = "SELECT d FROM Discount d WHERE d.createAt = :createAt"),
    @NamedQuery(name = "Discount.findByUpdateAt", query = "SELECT d FROM Discount d WHERE d.updateAt = :updateAt")})
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int offer;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isUsed = false;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status = true;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    public Discount() {
    }

    public Discount(Integer id) {
        this.id = id;
    }

    public Discount(Integer id, int offer, boolean isUsed, boolean status) {
        this.id = id;
        this.offer = offer;
        this.isUsed = isUsed;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.test.Discount[ id=" + id + " ]";
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
