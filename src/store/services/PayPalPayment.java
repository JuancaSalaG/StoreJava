package store.services;

import store.interfaces.PaymentMethod;

public class PayPalPayment implements PaymentMethod {

    @Override
    public void processingPay(double amount) {
        System.out.println("Processing payment with PayPal: " + amount);
    }
}
