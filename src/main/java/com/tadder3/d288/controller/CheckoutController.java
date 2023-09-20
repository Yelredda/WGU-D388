package com.tadder3.d288.controller;

import com.tadder3.d288.services.CheckoutService;
import com.tadder3.d288.services.PurchaseData;
import com.tadder3.d288.services.PurchaseResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    // Note, this was already implemented earlier than Step H in order to test the previous steps

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponseData placeOrder(@RequestBody PurchaseData purchaseData) {
        PurchaseResponseData purchaseResponse = checkoutService.placeOrder(purchaseData);

        return purchaseResponse;
    }
}
