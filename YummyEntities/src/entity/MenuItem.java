package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prakriti
 */
@Entity
@Table(name = "MENUITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", 
        query = "SELECT m FROM MenuItem m"),
    @NamedQuery(name = "MenuItem.findById", 
        query = "SELECT m FROM MenuItem m WHERE m.id = :id"),
    @NamedQuery(name = "MenuItem.findByName", 
        query = "SELECT m FROM MenuItem m WHERE m.name = :name"),
    @NamedQuery(name = "MenuItem.findByDescription", 
        query = "SELECT m FROM MenuItem m WHERE m.description = :description"),
    @NamedQuery(name = "MenuItem.findByPrice", 
        query = "SELECT m FROM MenuItem m WHERE m.price = :price"),
    @NamedQuery(name = "MenuItem.findByCategory", 
        query = "SELECT m FROM MenuItem m WHERE m.category = :category")})
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private float price;
    @Column(name = "CATEGORY")
    private String category;

    public MenuItem() {
    }

    public MenuItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && 
                !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MenuItem[ id=" + id + " ]";
    }
    
}
