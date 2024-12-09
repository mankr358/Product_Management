package com.example.Product_Managment_System.Service;

import com.example.Product_Managment_System.DTO.ProductDto;
import com.example.Product_Managment_System.DTO.ProductResponse;

import java.util.List;

public interface ProductService {

    Boolean saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    Boolean deleteProduct(Integer id);

    public ProductResponse getProductsWithPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
