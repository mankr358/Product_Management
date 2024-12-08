package com.example.Product_Managment_System.Service;

import com.example.Product_Managment_System.DTO.ProductDto;
import com.example.Product_Managment_System.Model.Product;
import com.example.Product_Managment_System.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Boolean saveProduct(ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException("ProductDto cannot be null");
        }

        // Map ProductDto to Product entity
        Product product = mapper.map(productDto, Product.class);

        // Save the product to the repository
        Product savedProduct = productRepository.save(product);

        // Check if the saved product is not empty (i.e., it was saved successfully)
        return !ObjectUtils.isEmpty(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        // Convert product list to product DTO list
        return productList.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Optional<Product> findByProduct = productRepository.findById(id);
        if (findByProduct.isPresent()) {
            Product product = findByProduct.get();
            return mapper.map(product, ProductDto.class);
        }
        return null; // Return null if product is not found
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        Optional<Product> findByProduct = productRepository.findById(id);
        if (findByProduct.isPresent()) {
            Product product = findByProduct.get();
            productRepository.delete(product);
            return true; // Return true if product is deleted successfully
        }
        return false; // Return false if product was not found to delete
    }
}
