package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.ProductRequest;
import com.ex.namperfume.dto.request.ProductSizeRequest;
import com.ex.namperfume.dto.response.ProductResponse;
import com.ex.namperfume.entity.*;
import com.ex.namperfume.exception.AppException;
import com.ex.namperfume.exception.EnumCode;
import com.ex.namperfume.mapper.BrandMapper;
import com.ex.namperfume.mapper.ProductMapper;
import com.ex.namperfume.mapper.ProductSizeMapper;
import com.ex.namperfume.repository.BrandRepository;
import com.ex.namperfume.repository.ProductRepository;
import com.ex.namperfume.repository.ProductTypeRepository;
import com.ex.namperfume.repository.SizeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {
    ProductRepository productRepository;
    BrandRepository brandRepository;
    ProductTypeRepository typeRepository;
    SizeRepository sizeRepository;

    ProductMapper productMapper;
    ProductSizeMapper productSizeMapper;
    BrandMapper brandMapper;

    //Create new product
    public ProductResponse createProduct(ProductRequest request){
        log.info("Creating new product: {}", request.getProductName());

        Product product = productMapper.toProduct(request);

        Brand brand = brandRepository.findById(request.getBrand_id()).orElseThrow(()-> new RuntimeException("Brand not found with id: "+ request.getBrand_id()));

        ProductType productType = typeRepository.findById(request.getType_id()).orElseThrow(() -> new RuntimeException("Product type not found with id: "+ request.getType_id()));

        product.setBrand(brand);
        product.setType(productType);

        Product saveProduct = productRepository.save(product);

        //Handle product sizes
        if (request.getProductSizes() != null &&
        !request.getProductSizes().isEmpty()){
            List<ProductSize> productSizes = request.getProductSizes().stream()
                    .map(sizeRequest -> {
                        ProductSize productSize = productSizeMapper.toProductSize(sizeRequest);

                        productSize.setProduct_price(
                                sizeRequest.getProduct_price() == 0 ? 0L : sizeRequest.getProduct_price()
                        );
                        //Set size relationship
                        Size size = sizeRepository.findById(sizeRequest.getSize_id()).orElseThrow(()-> new RuntimeException("Not found size with id: "+sizeRequest.getSize_id()));

                        productSize.setSize(size);
                        productSize.setProduct(saveProduct);
                        return productSize;
                    }).collect(Collectors.toList());

            saveProduct.setProductSizes(productSizes);
        }

        //Save again with product sizes
        Product finalProduct = productRepository.save(saveProduct);

        log.info("Product created successfully with id: {}", finalProduct.getProduct_id());
        return productMapper.toProductResponse(finalProduct);

    }

    //Get all products
    @Transactional
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse getProductById(UUID product_id){
        log.info("Fetching product with id: {}", product_id);

        Product product = productRepository.findById(product_id).orElseThrow(()-> new AppException(EnumCode.PRODUCT_NOT_EXIST));

        return productMapper.toProductResponse(product);
    }

    @Transactional
    public List<ProductResponse> getProductsByBrand(UUID brand_id){
        Brand brand = brandRepository.findById(brand_id).orElseThrow(()-> new AppException(EnumCode.BRAND_NOT_EXIST));

        List<Product> products = productRepository.findByBrand(brand);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Transactional
    public List<ProductResponse> getProductsByType(UUID type_id){
        ProductType type = typeRepository.findById(type_id).orElseThrow(()-> new AppException(EnumCode.PRODUCT_TYPE_NOT_EXIST));

        List<Product> products = productRepository.findByType(type);

        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Transactional
    public List<ProductResponse> getProductByName(String product_name){
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(product_name);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Transactional
    public List<ProductResponse> getProductsByFragranceFamily(String fragranceFamily){
        List<Product> products = productRepository.findByFragranceFamilyIgnoreCase(fragranceFamily);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @Transactional
    public List<ProductResponse> getProductsByStyle(String style){
        List<Product> products = productRepository.findByStyleIgnoreCase(style);
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    //Delete product by id
    @Transactional
    public void deleteProduct(UUID product_id){
        Product product = productRepository.findById(product_id).orElseThrow(()->new AppException(EnumCode.PRODUCT_NOT_EXIST));
        for (ProductSize size : product.getProductSizes()){
            size.setProduct(null);
        }
        product.getProductSizes().clear();
        productRepository.delete(product);
        log.info("Product deleted successfully with id: {}", product_id);
    }

    //Check product existed
    @Transactional
    public boolean productExistById(UUID product_id){
        return productRepository.existsById(product_id);
    }

    @Transactional
    public ProductResponse updateProduct(ProductRequest request, Product product){
        try{
            productMapper.updateProductFromDto(request, product);

            if(request.getBrand_id() != null){
                Brand brand =brandRepository.findById(request.getBrand_id())
                        .orElseThrow(()-> new AppException(EnumCode.BRAND_NOT_EXIST));
                product.setBrand(brand);
            }

            if(request.getType_id()!= null){
                ProductType type = typeRepository.findById(request.getType_id())
                        .orElseThrow(()-> new AppException(EnumCode.PRODUCT_TYPE_NOT_EXIST));
                product.setType(type);
            }

            if(request.getProductSizes() != null){
                product.getProductSizes().clear();

                List<ProductSize> newProductSizes = request.getProductSizes().stream().map(sizeRequest-> {
                            ProductSize productSize = productSizeMapper.toProductSize(sizeRequest);

                            productSize.setProduct_price(sizeRequest.getProduct_price() == null || sizeRequest.getProduct_price() == 0 ? 0L : sizeRequest.getProduct_price());

                            Size size = sizeRepository.findById(sizeRequest.getSize_id()).orElseThrow(()-> new AppException(EnumCode.SIZE_NOT_EXIST));

                            productSize.setSize(size);
                            productSize.setProduct(product);
                            return productSize;
                        }).collect(Collectors.toList());

                product.setProductSizes(newProductSizes);
            }
            Product savedProduct = productRepository.save(product);
            return productMapper.toProductResponse(savedProduct);
        }
        catch (Exception e){
            throw new RuntimeException("Can't update product: "+ e.getMessage());
        }
    }

    @Transactional
    public ProductResponse updateProductById(UUID product_id, ProductRequest request){
        try{
            Product existingProduct = productRepository.findById(product_id).orElseThrow(()-> new EntityNotFoundException("Not found product with id: "+product_id));

            return updateProduct(request, existingProduct);

        }catch (EntityNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Update product failed: "+e.getMessage());
        }
    }

    @Transactional
    public Product findProductById(UUID product_id) {
        return productRepository.findById(product_id)
                .orElseThrow(() -> new EntityNotFoundException("Not found product with id: " + product_id));
    }

}
