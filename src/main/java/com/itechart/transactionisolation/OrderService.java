package com.itechart.transactionisolation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void doOrder(Long productId, Long amount) {
        Product product = productRepository.getOne(productId);
        log.info("Get product");
        Order order = Order.builder().price(product.getPrice()).amount(amount).product(product).build();
        log.info("Save order");
        orderRepository.save(order);
    }
}
