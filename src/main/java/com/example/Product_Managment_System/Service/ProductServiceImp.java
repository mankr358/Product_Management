package com.example.Product_Managment_System.Service;

import com.example.Product_Managment_System.DTO.ProductDto;
import com.example.Product_Managment_System.DTO.ProductResponse;
import com.example.Product_Managment_System.Model.Product;
import com.example.Product_Managment_System.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.boot.web.server.Ssl.ClientAuth.map;


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

    @Override
    public ProductResponse getProductsWithPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        //
//      Sort sort=  Sort.by(sortBy).ascending();
//      Sort sort2=  Sort.by(sortBy).descending();

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Create a pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Fetch paginated products from the repository
        Page<Product> page = productRepository.findAll(pageable);
        // Map the products to ProductDto
        List<Product> products = null;
        List<ProductDto> productsDtos = products.stream()
                .map(prod -> mapper.map(prod, ProductDto.class))
                .collect(Collectors.toList());

        // int pageNo= page.getNumber();

        // Fetch additional pagination details
        products = page.getContent();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        boolean first = page.isFirst();
        boolean last = page.isLast();

//        ProductResponse productResponse = new ProductResponse();
//        productResponse.setProducts(productsDtos);
//        productResponse.setTotalElements(totalElements);
        // Construct the ProductResponse object
        ProductResponse productResponse = ProductResponse.builder().products(productsDtos).totalElements(totalElements)
                .totalPages(totalPages).isFirst(first).isLast(last).pageNo(pageNo).pageSize(pageSize).build();

        return productResponse;
    }
}
