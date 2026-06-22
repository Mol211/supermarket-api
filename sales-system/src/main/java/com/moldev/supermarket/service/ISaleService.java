package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.dto.SaleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    SaleDTO registerSale(SaleDTO saleDTO);
    List<SaleDTO> getSales();
    SaleDTO updateSale(Long id, SaleDTO saleDTO);
    List<SaleDTO> getSalesBySucursalAndDate(Long sucursalId, LocalDate date);
    void disableSale(Long id);

    ProductDTO getProductMostSold();
}
