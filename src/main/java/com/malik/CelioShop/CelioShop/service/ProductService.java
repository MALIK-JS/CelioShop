package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto productDto, ProductCategoryDto productCategoryDto);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts();

    void deleteProductById(Long productId);

    void updateProductById(Long productId);
}
