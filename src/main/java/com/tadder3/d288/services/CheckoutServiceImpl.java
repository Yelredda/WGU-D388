package com.tadder3.d288.services;

import com.tadder3.d288.dao.CartItemRepository;
import com.tadder3.d288.dao.CartRepository;
import com.tadder3.d288.dao.CustomerRepository;
import com.tadder3.d288.entities.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }
    @Override
    @Transactional
    public PurchaseResponseData placeOrder(PurchaseData purchaseData) {

        try {

            if (purchaseData.getCustomer() == null) {
                throw new Exception("ERROR: The customer cannot be null");
            }

            // Assuming that a price of 0 means that nothing has been selected
            if (purchaseData.getCart().getPackage_price().intValue() == 0 ) {
                throw new Exception("ERROR: The cart cannot be empty");
            }

            // Create new cart to save to CartRepository using Cart from purchaseData
            Cart cart = purchaseData.getCart();
            Cart newCart = new Cart();

            // Populate newCart data
            String orderTrackingNumber = generateOrderTrackingNumber();
            newCart.setOrderTrackingNumber(orderTrackingNumber);
            newCart.setStatus(StatusType.ordered);
            newCart.setCustomer(cart.getCustomer());
            newCart.setParty_size(cart.getParty_size());
            newCart.setPackage_price(cart.getPackage_price());
            cartRepository.save(newCart);

            Set<CartItem> cartItems = purchaseData.getCartItems();

            Customer customer = purchaseData.getCustomer();

            // populate cart items
            for (CartItem cartItem : cartItems) {
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(newCart);
                newCartItem.setExcursions(cartItem.getExcursions());
                newCartItem.setVacation(cartItem.getVacation());

                System.out.println("Troubleshooting: ");
                System.out.println("Cart Item ID " + newCartItem.getId());
                cartItemRepository.save(newCartItem);
            }

            // return a response
            return new PurchaseResponseData(orderTrackingNumber);
        } catch (Exception exception) {
            return new PurchaseResponseData( exception.getMessage() );
        }
    }

    private String generateOrderTrackingNumber() {
        // Generate a random UUID
        return UUID.randomUUID().toString();
    }


}
