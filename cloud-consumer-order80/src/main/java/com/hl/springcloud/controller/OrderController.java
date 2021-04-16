package com.hl.springcloud.controller;

import com.hl.myrule.MySelfRule;
import com.hl.springcloud.entities.CommonResult;
import com.hl.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderController {
    static final String PAYMENTURL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("serial: "+payment.getSerial());
        return restTemplate.postForObject(PAYMENTURL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENTURL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getforentity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity entity = restTemplate.getForEntity(PAYMENTURL+"/payment/get/"+id,CommonResult.class);
        log.info("getforentity请求状态码是："+entity.getStatusCode().toString());
        if(entity.getStatusCode().is2xxSuccessful()){
            return (CommonResult<Payment>) entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/zpkin")
    public String zpkin(){
        return restTemplate.getForObject(PAYMENTURL+"/payment/zpkin",String.class);
    }
}
