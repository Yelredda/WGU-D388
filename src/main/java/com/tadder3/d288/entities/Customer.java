package com.tadder3.d288.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    long id;

    @Column(name = "customer_first_name")
    String firstName;

    @Column(name = "customer_last_name")
    String lastName;

    @Column(name = "address")
    String address;

    @Column(name = "postal_code")
    String postal_code;

    @Column(name = "phone")
    String phone;

    @ManyToOne
    @JoinColumn(name="division_id") //, nullable = false)
    Division division;

    @OneToMany(mappedBy = "customer")
    Set<Cart> carts = new HashSet<Cart>();

    @Column(name = "create_Date")
    @CreationTimestamp
    Date create_date;

    @Column(name = "last_Update")
    @UpdateTimestamp
    Date last_update;
}
