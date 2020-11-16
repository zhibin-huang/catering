package me.catring.demo.domain.entity;

import javax.persistence.*;


@Entity
@Table(name = "CartItem")
public class CartItem {
    private int quantity;
    private int cartItemId;
    private User userByUserId;
    private Food foodByFoodId;

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "cart_item_id", nullable = false)
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public String toString() {
        return "Cart Item: " + getCartItemId() + ", " + getUserByUserId().getUserId() + ", " + getFoodByFoodId().getFoodTitle();
    }

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    public Food getFoodByFoodId() {
        return foodByFoodId;
    }
    public void setFoodByFoodId(Food foodByFoodId) {
        this.foodByFoodId = foodByFoodId;
    }
}
