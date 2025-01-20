package com.payment.Paymant.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
