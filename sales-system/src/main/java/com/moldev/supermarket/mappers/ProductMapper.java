package com.moldev.supermarket.mappers;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.model.Product;

public class ProductMapper {
    public static ProductDTO productToDTO (Product p){
        if (p == null) return null;
        return new ProductDTO(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory(),
                p.getStock()
        );
    }
    public static Product prodDTOToProduct (ProductDTO pDTO){
        return new Product(
                pDTO.getId(),
                pDTO.getName(),
                pDTO.getPrice(),
                pDTO.getCategory(),
                pDTO.getStock()
        );
    }
}
