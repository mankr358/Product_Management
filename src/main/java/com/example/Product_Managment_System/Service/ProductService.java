package com.example.Product_Managment_System.Service;

import com.example.Product_Managment_System.DTO.ProductDto;

import java.util.List;

public interface ProductService {

    Boolean saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    Boolean deleteProduct(Integer id);
}
