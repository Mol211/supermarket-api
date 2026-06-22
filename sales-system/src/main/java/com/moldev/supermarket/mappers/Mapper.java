package com.moldev.supermarket.mappers;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.dto.SaleDTO;
import com.moldev.supermarket.dto.SaleDetailDTO;
import com.moldev.supermarket.dto.SucursalDTO;
import com.moldev.supermarket.model.Product;
import com.moldev.supermarket.model.Sale;
import com.moldev.supermarket.model.Sucursal;

import java.math.BigDecimal;
import java.util.List;

public class Mapper {
    public static ProductDTO toDTO(Product p){
        if (p == null) return null;
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .category(p.getCategory())
                .stock(p.getStock())
                .build();
    }
    public static SaleDTO toDTO(Sale s){
        if(s == null) return null;

        List<SaleDetailDTO> detallesDTO = s.getDetailList().stream().map(detail->
                SaleDetailDTO.builder()
                        .id(detail.getId())
                        .productName(detail.getProduct().getName())
                        .productQuantity(detail.getProductQuantity())
                        .price(detail.getUnitPrice())
                        .subtotal(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getProductQuantity())))
                        .build()
        ).toList();

        List<BigDecimal> total = detallesDTO.stream().map(SaleDetailDTO::getSubtotal).toList();
        BigDecimal calculatedTotal = total.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal algo = BigDecimal.ZERO;
//        for(BigDecimal bg : total) {
//            algo = algo.add(bg);
//        }


        return SaleDTO.builder()
                .id(s.getId())
                .date(s.getDate())
                .status(s.getStatus())
                .idSucursal(s.getSucursal().getId())
                //Arreglar esto
                .detalle(detallesDTO)
                .total(calculatedTotal)
                .build();
    }
    public static SucursalDTO toDTO(Sucursal s){
        if (s == null) return null;
        return SucursalDTO.builder()
                .id(s.getId())
                .name(s.getName())
                .direction(s.getDirection())
                .build();
    }

}
