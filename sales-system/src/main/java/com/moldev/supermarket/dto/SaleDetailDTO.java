package com.moldev.supermarket.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long id;
    private String productName;
    private Integer productQuantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}
