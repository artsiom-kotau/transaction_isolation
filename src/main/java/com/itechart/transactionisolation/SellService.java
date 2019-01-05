package com.itechart.transactionisolation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class SellService {

    private static final BigDecimal DIFF = BigDecimal.valueOf(10);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CountDownLatch latch;

    @Transactional
    public void updatePrice() {
        log.info("Start price updating. Thread '{}'", Thread.currentThread().getName());
        Collection<Product> products = productRepository.findAll();
        products.forEach(this::updatePrice);
        productRepository.saveAll(products);
        entityManager.flush();
        log.info("Flash changes and wait for order transaction");
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("Something wrong");
            Thread.currentThread().interrupt();
        }
        log.error("Rollback changes");
        throw new RuntimeException("Unexpected exception");

    }

    private void updatePrice(Product product) {
        log.info("Update price");
        BigDecimal currentPrice = product.getPrice();
        product.setPrice(currentPrice.add(DIFF));
    }
}
