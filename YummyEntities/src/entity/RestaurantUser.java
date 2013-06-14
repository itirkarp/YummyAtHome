/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Prakriti
 */
@Entity
@Table(name = "RESTAURANTUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantUser.findAll", query = "SELECT r FROM RestaurantUser r"),
    @NamedQuery(name = "RestaurantUser.findById", query = "SELECT r FROM RestaurantUser r WHERE r.id = :id"),
    @NamedQuery(name = "RestaurantUser.findByName", query = "SELECT r FROM RestaurantUser r WHERE r.name = :name"),
    @NamedQuery(name = "RestaurantUser.findByPassword", query = "SELECT r FROM RestaurantUser r WHERE r.password = :password"),
    @NamedQuery(name = "RestaurantUser.findByEmail", query = "SELECT r FROM RestaurantUser r WHERE r.email = :email"),
    @NamedQuery(name = "RestaurantUser.findByPhone", query = "SELECT r FROM RestaurantUser r WHERE r.phone = :phone"),
    @NamedQuery(name = "RestaurantUser.findByAddress", query = "SELECT r FROM RestaurantUser r WHERE r.address = :address")})
public class RestaurantUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @OneToMany(mappedBy = "userid")
    private Collection<RestaurantOrder> restaurantOrderCollection;

    public RestaurantUser() {
    }

    public RestaurantUser(String name, String password, String email, String phone, String address) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public RestaurantUser(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<RestaurantOrder> getRestaurantOrderCollection() {
        return restaurantOrderCollection;
    }

    public void setRestaurantOrderCollection(Collection<RestaurantOrder> restaurantOrderCollection) {
        this.restaurantOrderCollection = restaurantOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RestaurantUser)) {
            return false;
        }
        RestaurantUser other = (RestaurantUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantUser[ id=" + id + " ]";
    }
    
}
