package com.itechart.transactionisolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SellService {

    private static final BigDecimal DIFF = BigDecimal.valueOf(10);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    private CyclicBarrier cyclicBarrier;

    public SellService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Transactional
    public void updatePrice() throws BrokenBarrierException, InterruptedException {
        Collection<Product> products = productRepository.findAll();
        products.forEach(this::updatePrice);
        entityManager.flush();
        cyclicBarrier.await();
        throw new RuntimeException("Unexpected exception");

    }

    private void updatePrice(Product product) {
        BigDecimal currentPrice = product.getPrice();
        product.setPrice(currentPrice.add(DIFF));
    }
}
