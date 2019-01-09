package com.itechart.transactionisolation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void doOrder(Long productId, Long amount) {
        Product product = productRepository.findById(productId).get();
        log.info("Get product");
        Order order = Order.builder().product(product).price(product.getPrice()).amount(amount).build();
        orderRepository.save(order);
        log.info("Save order");
    }


}
