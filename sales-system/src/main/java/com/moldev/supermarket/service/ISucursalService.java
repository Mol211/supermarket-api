package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {
    List<SucursalDTO> getSucursals();
    SucursalDTO createSucursal(SucursalDTO sucursalDTO);
    SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO);
    void deleteSucursal(Long id);
}
