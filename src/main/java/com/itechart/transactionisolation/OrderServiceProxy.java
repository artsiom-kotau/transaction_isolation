package com.itechart.transactionisolation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class OrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CountDownLatch latch;

    public void doOrder(Long productId, Long amount) {
        log.info("Start do order. Thread '{}'", Thread.currentThread().getName());
        orderService.doOrder(productId, amount);
        log.info("Order transaction committed");
        latch.countDown();
        log.info("Finish order process");
    }
}
