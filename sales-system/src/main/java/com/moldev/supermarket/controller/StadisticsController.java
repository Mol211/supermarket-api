package com.moldev.supermarket.controller;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.service.IProductService;
import com.moldev.supermarket.service.ISaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
public class StadisticsController {
    private final ISaleService saleService;

    public StadisticsController(ISaleService saleService) {
        this.saleService = saleService;
    }


    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ProductDTO> getProductMostSold() {
        return ResponseEntity.ok(saleService.getProductMostSold());
    }
}
