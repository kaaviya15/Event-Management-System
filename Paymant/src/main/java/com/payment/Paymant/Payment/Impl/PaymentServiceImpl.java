package com.payment.Paymant.Payment.Impl;

import com.payment.Paymant.Payment.Payment;
import com.payment.Paymant.Payment.PaymentRepo;
import com.payment.Paymant.Payment.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public void createService(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public List<Payment> getAllPayment() {
        List<Payment> paymentList=paymentRepo.findAll();
        return paymentList;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id).orElse(null);
    }

    @Override
    public boolean updateById(Long id, Payment payment) {
        Optional<Payment> toUpdate=paymentRepo.findById(id);
        if(toUpdate.isPresent()){
            Payment updatedPayment=toUpdate.get();
            updatedPayment.setPaymentMethod(payment.getPaymentMethod());
            updatedPayment.setTicketId(payment.getTicketId());
            updatedPayment.setUserId(payment.getUserId());
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setStatus(payment.getStatus());
            paymentRepo.save(updatedPayment);
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (paymentRepo.existsById(id)) {
            paymentRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
