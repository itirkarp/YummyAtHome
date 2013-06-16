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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prakriti
 */
@Entity
@Table(name = "ORDERITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", 
        query = "SELECT o FROM OrderItem o"),
    @NamedQuery(name = "OrderItem.findById", 
        query = "SELECT o FROM OrderItem o WHERE o.id = :id"),
    @NamedQuery(name = "OrderItem.findByQuantity", 
        query = "SELECT o FROM OrderItem o WHERE o.quantity = :quantity")})
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ID")
    @ManyToOne
    private RestaurantOrder orderid;
    @JoinColumn(name = "ITEMID", referencedColumnName = "ID")
    @OneToOne
    private MenuItem itemid;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, RestaurantOrder orderid, MenuItem itemid) {
        this.quantity = quantity;
        this.orderid = orderid;
        this.itemid = itemid;
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public RestaurantOrder getOrderid() {
        return orderid;
    }

    public void setOrderid(RestaurantOrder orderid) {
        this.orderid = orderid;
    }

    public MenuItem getItemid() {
        return itemid;
    }

    public void setItemid(MenuItem itemid) {
        this.itemid = itemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && 
                !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderItem[ id=" + id + " ]";
    }
    
}
