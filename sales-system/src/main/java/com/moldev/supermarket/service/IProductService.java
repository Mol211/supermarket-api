package com.moldev.supermarket.service;

import com.moldev.supermarket.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO getProduct(Long id);
    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
