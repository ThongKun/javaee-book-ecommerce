/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
@Table(name = "shopping_book", catalog = "lab02", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ShoppingBook.findAll", query = "SELECT s FROM ShoppingBook s"),
    @NamedQuery(name = "ShoppingBook.findByQuantity", query = "SELECT s FROM ShoppingBook s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "ShoppingBook.findById", query = "SELECT s FROM ShoppingBook s WHERE s.id = :id"),
    @NamedQuery(name = "ShoppingBook.findByStatus", query = "SELECT s FROM ShoppingBook s WHERE s.status = :status")})
public class ShoppingBook implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(nullable = false)
    private int quantity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "shopping_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Shopping shopping;

    public ShoppingBook() {
    }

    public ShoppingBook(Integer id) {
        this.id = id;
    }

    public ShoppingBook(Integer id, int quantity, boolean status) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
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
        if (!(object instanceof ShoppingBook)) {
            return false;
        }
        ShoppingBook other = (ShoppingBook) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.test.ShoppingBook[ id=" + id + " ]";
    }

}
