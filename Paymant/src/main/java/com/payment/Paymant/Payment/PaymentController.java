package com.payment.Paymant.Payment;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayment(){
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id){
        Payment payment=paymentService.getPaymentById(id);
        if(payment!=null){
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(payment,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createService(@RequestBody Payment payment){
        paymentService.createService(payment);
        return new ResponseEntity<>("Payment Created SuccessFully",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Payment payment){
        boolean isUpdated= paymentService.updateById(id,payment);
        if(isUpdated){
            return new ResponseEntity<>("Payment Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Payment Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted= paymentService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("Payment Successfully Deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Payment Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
