package com.hl.springcloud.controller;

import com.hl.springcloud.entities.CommonResult;
import com.hl.springcloud.entities.Payment;
import com.hl.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String paymentTimeout(){
        return paymentFeignService.paymentTimeout();
    };
}
