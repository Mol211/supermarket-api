package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.dto.SaleDTO;
import com.moldev.supermarket.dto.SaleDetailDTO;
import com.moldev.supermarket.exception.NotFoundException;
import com.moldev.supermarket.mappers.Mapper;
import com.moldev.supermarket.model.Product;
import com.moldev.supermarket.model.Sale;
import com.moldev.supermarket.model.SaleDetail;
import com.moldev.supermarket.model.Sucursal;
import com.moldev.supermarket.repository.ProductRepository;
import com.moldev.supermarket.repository.SaleRepository;
import com.moldev.supermarket.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class SaleService implements  ISaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SucursalRepository sucursalRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository, SucursalRepository sucursalRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public SaleDTO registerSale(SaleDTO saleDTO) {

        if (saleDTO == null) throw new RuntimeException("VentaDTO es null");
        if (saleDTO.getIdSucursal() == null) throw new RuntimeException("Sucursal no puede ser null");
        if (saleDTO.getDetalle() == null || saleDTO.getDetalle().isEmpty() )
            throw new RuntimeException("Debe incluir al menos un producto en la venta");

        //Buscar sucursal
        Sucursal suc = sucursalRepository.findById(saleDTO.getIdSucursal())
                .orElseThrow(()-> new NotFoundException("No se ha encontrado la sucursal indicada"));
        Sale sale = new Sale();
        sale.setStatus(saleDTO.getStatus());
        sale.setDate(saleDTO.getDate());
        sale.setSucursal(suc);

        List<SaleDetail> details = new ArrayList<>();

        BigDecimal totalCalculated = BigDecimal.ZERO;

        for(SaleDetailDTO dto : saleDTO.getDetalle()) {
            Product p = productRepository.findByName(dto.getProductName())
                    .orElseThrow(()-> new NotFoundException("Producto no encontrado: "+dto.getProductName()));
            p.setStock(p.getStock()-dto.getProductQuantity());
            productRepository.save(p);
            SaleDetail detail = new SaleDetail();
            detail.setProduct(p);
            detail.setUnitPrice(p.getPrice());
            detail.setProductQuantity(dto.getProductQuantity());
            detail.setSale(sale);

            details.add(detail);

            totalCalculated = totalCalculated.add(p.getPrice().multiply(BigDecimal.valueOf(dto.getProductQuantity())));

        }
        sale.setDetailList(details);
        sale.setTotal(totalCalculated);

        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public List<SaleDTO> getSales() {
        return saleRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale s = saleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se ha encontrado esa venta"));
        if(saleDTO.getDate() != null){
            s.setDate(saleDTO.getDate());
        }
        if(saleDTO.getStatus() != null) {
            s.setStatus(saleDTO.getStatus());
        }
        if(saleDTO.getDetalle() != null) {
            for(SaleDetail oldDetail : s.getDetailList()) {
                Product p = oldDetail.getProduct();
                p.setStock(p.getStock() + oldDetail.getProductQuantity());
            }
            s.getDetailList().clear();


            BigDecimal totalCalculated = BigDecimal.ZERO;

            for (SaleDetailDTO dto : saleDTO.getDetalle()) {
                Product p = productRepository.findByName(dto.getProductName())
                        .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + dto.getProductName()));
                p.setStock(p.getStock() - dto.getProductQuantity());
                productRepository.save(p);
                SaleDetail detail = new SaleDetail();
                detail.setProduct(p);
                detail.setUnitPrice(p.getPrice());
                detail.setProductQuantity(dto.getProductQuantity());
                detail.setSale(s);
                s.getDetailList().add(detail);
                totalCalculated = totalCalculated.add(p.getPrice().multiply(BigDecimal.valueOf(dto.getProductQuantity())));
            }
            s.setTotal(totalCalculated);
        }
        if(saleDTO.getIdSucursal() != null) {
            Sucursal suc = sucursalRepository.findById(saleDTO.getIdSucursal())
                    .orElseThrow(()-> new NotFoundException("No se ha encontrado la sucursal indicada"));
            s.setSucursal(suc);
        }
        return Mapper.toDTO(saleRepository.save(s));
    }


    @Override
    public List<SaleDTO> getSalesBySucursalAndDate(Long sucursalId, LocalDate date) {
        return List.of();
    }

    @Override
    public void disableSale(Long id) {
        if (saleRepository.existsById(id)) throw new NotFoundException("Venta no encontrada");
        saleRepository.deleteById(id);

    }

    @Override
    public ProductDTO getProductMostSold() {
        var id = saleRepository.findTopProductsIds()
                .stream().findFirst().orElseThrow(()->new RuntimeException("No sales found"));
        Product p = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found"));
        return new ProductDTO(p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory(),
                p.getStock());

    }
}
