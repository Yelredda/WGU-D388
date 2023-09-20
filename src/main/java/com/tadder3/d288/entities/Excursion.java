package com.tadder3.d288.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="excursions")
@Getter
@Setter
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    Long id;

    @Column(name = "excursion_title")
    String excursion_title;

    @Column(name = "image_url")
    String image_URL;

    @Column(name = "excursion_price")
    BigDecimal excursion_price;

    @ManyToOne
    @JoinColumn(name="vacation_id",nullable = false)
    Vacation vacation;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="excursion_cartitem", joinColumns = @JoinColumn(name="cart_item_id"), inverseJoinColumns = @JoinColumn(name="excursion_id"))
    Set<CartItem> cartitems;

    @Column(name = "create_date")
    @CreationTimestamp
    Date create_date;

    @Column(name = "last_Update")
    @UpdateTimestamp
    Date last_update;
}
