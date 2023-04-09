package com.xyz.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "t_order_line_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
