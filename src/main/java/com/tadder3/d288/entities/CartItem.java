package com.tadder3.d288.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cart_items")
@Getter
@Setter

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    Long id;

    @ManyToOne
    @JoinColumn(name="vacation_id",nullable = false)
    Vacation vacation;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="excursion_cartitem", joinColumns = @JoinColumn(name="cart_item_id"), inverseJoinColumns = @JoinColumn(name="excursion_id"))
    Set<Excursion> excursions = new HashSet<Excursion>();

    @ManyToOne
    @JoinColumn(name="cart_id",nullable = false)
    Cart cart;

    @Column(name = "create_Date")
    @CreationTimestamp
    Date create_date;

    @Column(name = "last_Update")
    @UpdateTimestamp
    Date last_update;
}
