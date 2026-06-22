package com.moldev.supermarket.controller;

import com.moldev.supermarket.dto.SucursalDTO;
import com.moldev.supermarket.model.Sucursal;
import com.moldev.supermarket.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursals")
public class SucursalController {
    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getSucursals() {
        return ResponseEntity.ok(sucursalService.getSucursals());
    }
    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO dto) {
        SucursalDTO sucursal = sucursalService.createSucursal(dto);
        return ResponseEntity.created(URI.create("/api/sucursals/"+sucursal.getId())).body(sucursal);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(
                @PathVariable Long id,
                @RequestBody SucursalDTO dto) {
        return ResponseEntity.ok(sucursalService.updateSucursal(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }

}
