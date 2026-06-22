package com.moldev.supermarket.mappers;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.dto.SaleDetailDTO;
import com.moldev.supermarket.model.Product;
import com.moldev.supermarket.model.SaleDetail;

import java.math.BigDecimal;

public class SaleDetailMapper {
    public static SaleDetailDTO saleDetailToDTO (SaleDetail saleDetail){
        return new SaleDetailDTO(
                saleDetail.getId(),
                saleDetail.getProduct().getName(),
                saleDetail.getProductQuantity(),
                saleDetail.getUnitPrice(),
                saleDetail.getUnitPrice().multiply(BigDecimal.valueOf(saleDetail.getProductQuantity()))
        );
    }
//    public static SaleDetail saleDetailDTOtoSaleDetail (SaleDetailDTO sDTO){
//        return new SaleDetail(
//                sDTO.getId(),
//                sDTO.
//        );
//    }
}
