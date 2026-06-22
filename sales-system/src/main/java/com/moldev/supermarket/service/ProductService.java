package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.ProductDTO;
import com.moldev.supermarket.exception.NotFoundException;
import com.moldev.supermarket.mappers.Mapper;
import com.moldev.supermarket.model.Product;
import com.moldev.supermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements  IProductService{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return Mapper.toDTO(productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encuentra ese producto")));
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return Mapper.toDTO(
                productRepository.save(
                        Product.builder()
                            .name(productDTO.getName())
                            .price(productDTO.getPrice())
                            .category(productDTO.getCategory())
                            .stock(productDTO.getStock())
                            .build()
                )
        );
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encuentra ese producto"));
        p.setName(productDTO.getName());
        p.setCategory(productDTO.getCategory());
        p.setStock(productDTO.getStock());
        p.setPrice(productDTO.getPrice());
        productRepository.save(p);
        return Mapper.toDTO(p);

    }

    @Override
    public void deleteProduct(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encuentra ese producto"));
        productRepository.delete(p);
    }
}
