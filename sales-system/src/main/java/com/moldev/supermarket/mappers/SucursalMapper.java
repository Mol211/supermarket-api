package com.moldev.supermarket.mappers;

import com.moldev.supermarket.dto.SucursalDTO;
import com.moldev.supermarket.model.Sucursal;

public class SucursalMapper {
    public static SucursalDTO sucursalToDTO (Sucursal s){
        if (s == null) return null;
        return new SucursalDTO(
                s.getId(),
                s.getName(),
                s.getDirection()
        );
    }
    public static Sucursal sucursalDTOToSucursal (SucursalDTO sDTO){
        return new Sucursal(
                sDTO.getId(),
                sDTO.getName(),
                sDTO.getDirection()
        );
    }
}
