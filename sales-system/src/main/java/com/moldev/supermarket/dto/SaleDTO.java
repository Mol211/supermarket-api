package com.moldev.supermarket.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    //Datos de la venta
    private Long id;
    private LocalDate date;
    private String status;

    //Datos de la sucursal
    private Long idSucursal;

    //Lista de detalles
    private List<SaleDetailDTO> detalle;

    //Total de la venta
    private BigDecimal total;


}
