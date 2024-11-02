package com.martinatanasov.springgraphql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private BigDecimal currency;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp created;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp finalized;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
