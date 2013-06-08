package entity;

/**
 *
 * @author Prakriti
 */
public class CartItem {
    public Integer id;
    public Integer quantity;
    public String name;
    private float price;

    public CartItem(Integer id, Integer quantity, String name, float price) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

}
