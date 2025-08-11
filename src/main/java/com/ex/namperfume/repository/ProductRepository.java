package com.ex.namperfume.repository;

import com.ex.namperfume.entity.Brand;
import com.ex.namperfume.entity.Product;
import com.ex.namperfume.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByBrand(Brand brand);

    List<Product> findByType(ProductType type);

    List<Product> findByProductNameContainingIgnoreCase(String product_name);

    List<Product> findByFragranceFamilyIgnoreCase(String fragrance_family);

    List<Product> findByStyleIgnoreCase(String style);

    List<Product> findByBrandAndType(Brand brand, ProductType type);


}
