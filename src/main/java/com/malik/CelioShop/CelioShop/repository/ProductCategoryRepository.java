package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    Optional<ProductCategory> findByName(String name);
}
