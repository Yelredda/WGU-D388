package com.tadder3.d288.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    Long id;

    @Column(name = "order_Tracking_Number")
    String orderTrackingNumber;

    @Column(name = "package_Price")
    BigDecimal package_price;

    @Column(name = "party_Size")
    int party_size;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusType status;

    @Column(name = "create_Date")
    @CreationTimestamp
    Date create_date;

    @Column(name = "last_Update")
    @UpdateTimestamp
    Date last_update;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    Customer customer;

    @OneToMany(mappedBy = "cart")
    Set<CartItem> cartItem = new HashSet<CartItem>();

    public void add(CartItem item) {
        if (item != null) {
            if (cartItem == null) {
                cartItem = new HashSet<>();
            }

            cartItem.add(item);
            item.setCart(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
