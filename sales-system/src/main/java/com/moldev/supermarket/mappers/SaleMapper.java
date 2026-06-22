package com.moldev.supermarket.mappers;

import com.moldev.supermarket.dto.SaleDTO;
import com.moldev.supermarket.model.Sale;
import com.moldev.supermarket.model.Sucursal;

import java.util.List;

public class SaleMapper {
    public static SaleDTO saletoSaleDTO (Sale s){
        return new SaleDTO(
                s.getId(),
                s.getDate(),
                s.getStatus(),
                s.getSucursal().getId(),
                //Arreglar esto
                List.of(),
                s.getTotal()
        );
    }
    public static Sale saleDTOtoSale (SaleDTO sDTO){
        return new Sale(
                sDTO.getId(),
                sDTO.getDate(),
                sDTO.getStatus(),
                sDTO.getTotal(),
                new Sucursal(),
//                sDTO.getDetalle()
                List.of()
        );
    }
}
