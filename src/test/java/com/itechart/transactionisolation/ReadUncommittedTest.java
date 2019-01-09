package com.itechart.transactionisolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mysql")
public class ReadUncommittedTest {

    private static Long PRODUCT_ID = 1l;
    private static Long AMOUNT = 10l;

    @Autowired
    private SellService sellService;

    @Autowired
    private OrderServiceProxy orderServiceProxy;

    @Test
    public void doTest() throws InterruptedException {
        Thread sellServiceThread = new Thread(() -> sellService.updatePrice());
        Thread orderServiceThread = new Thread(() -> orderServiceProxy.doOrder(PRODUCT_ID, AMOUNT));
        sellServiceThread.start();
        Thread.sleep(10000);
        orderServiceThread.start();
        orderServiceThread.join();
    }


}

