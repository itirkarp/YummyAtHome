package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Prakriti
 */
@Entity
@Table(name = "RESTAURANTORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantOrder.findAll", query = "SELECT r FROM RestaurantOrder r"),
    @NamedQuery(name = "RestaurantOrder.findById", query = "SELECT r FROM RestaurantOrder r WHERE r.id = :id"),
    @NamedQuery(name = "RestaurantOrder.findByOrderdate", query = "SELECT r FROM RestaurantOrder r WHERE r.orderdate = :orderdate"),
    @NamedQuery(name = "RestaurantOrder.findByTotal", query = "SELECT r FROM RestaurantOrder r WHERE r.total = :total")})
public class RestaurantOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private float total;
    @OneToMany(mappedBy = "orderid")
    private Collection<OrderItem> orderItemCollection;
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private RestaurantUser userid;

    public RestaurantOrder() {
    }

    public RestaurantOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    public RestaurantUser getUserid() {
        return userid;
    }

    public void setUserid(RestaurantUser userid) {
        this.userid = userid;
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
        if (!(object instanceof RestaurantOrder)) {
            return false;
        }
        RestaurantOrder other = (RestaurantOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantOrder[ id=" + id + " ]";
    }
    
}
