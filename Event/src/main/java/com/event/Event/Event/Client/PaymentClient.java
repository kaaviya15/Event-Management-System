package com.event.Event.Event.Client;


import com.event.Event.Event.external.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "payment")
public interface PaymentClient {

    @GetMapping("/payment")
    List<Payment> getPayment(@RequestParam("paymentId") Long paymentId);
}
