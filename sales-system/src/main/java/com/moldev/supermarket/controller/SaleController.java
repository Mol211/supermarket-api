package com.moldev.supermarket.controller;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.dto.SaleDTO;
import com.moldev.supermarket.model.Product;
import com.moldev.supermarket.model.Sale;
import com.moldev.supermarket.service.IProductService;
import com.moldev.supermarket.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.ok(saleService.getSales());
    }
    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO dto) {
        SaleDTO createdSale = saleService.registerSale(dto);
        return ResponseEntity.created(URI.create("/api/sales/"+createdSale.getId()))
                .body(createdSale);

    }
    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO dto) {
        return ResponseEntity.ok(saleService.updateSale(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.disableSale(id);
        return ResponseEntity.noContent().build();
    }














}
