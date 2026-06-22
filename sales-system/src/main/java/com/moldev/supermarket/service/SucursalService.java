package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.SucursalDTO;
import com.moldev.supermarket.exception.NotFoundException;
import com.moldev.supermarket.mappers.Mapper;
import com.moldev.supermarket.mappers.SucursalMapper;
import com.moldev.supermarket.model.Sucursal;
import com.moldev.supermarket.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements  ISucursalService {
    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<SucursalDTO> getSucursals() {
        List<Sucursal> sucursalList = sucursalRepository.findAll();
        return sucursalList.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDTO) {

                 Sucursal s = sucursalRepository.save(
                         Sucursal.builder()
                                 .name(sucursalDTO.getName())
                                 .direction(sucursalDTO.getDirection())
                                 .build());
        System.out.println(s.toString());
        return Mapper.toDTO(s);
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal s = sucursalRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se ha encontrado esa sucursal"));
        s.setDirection(sucursalDTO.getDirection());
        s.setName(sucursalDTO.getName());
        return Mapper.toDTO(s);
    }

    @Override
    public void deleteSucursal(Long id) {
        if(!sucursalRepository.existsById(id)) throw new NotFoundException("No se ha encontrado esa sucursal para su elminación");
        sucursalRepository.deleteById(id);
    }
}
