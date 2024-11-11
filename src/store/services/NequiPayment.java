package store.services;

import store.interfaces.PaymentMethod;

public class NequiPayment implements PaymentMethod {

    @Override
    public void processingPay(double amount) {
        System.out.println("Processing payment with Nequi: " + amount);
    }
}
