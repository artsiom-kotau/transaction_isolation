package com.itechart.transactionisolation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@SpringBootApplication
public class TransactionIsolationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionIsolationApplication.class, args);
    }

    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }

}

