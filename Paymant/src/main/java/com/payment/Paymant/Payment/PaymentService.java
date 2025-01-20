package com.payment.Paymant.Payment;

import java.util.List;

public interface PaymentService {

    void createService(Payment payment);
    List<Payment> getAllPayment();
    Payment getPaymentById(Long id);
    boolean updateById(Long id,Payment payment);
    boolean deleteById(Long id);
}
