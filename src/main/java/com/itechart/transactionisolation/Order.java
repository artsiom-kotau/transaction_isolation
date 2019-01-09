package com.itechart.transactionisolation;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_order")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long amount;

    @Column
    private BigDecimal price;
}
