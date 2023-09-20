package com.tadder3.d288.services;

import com.tadder3.d288.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class PurchaseData {
    Cart cart;
    Set<CartItem> cartItems;
    Customer customer;
}
