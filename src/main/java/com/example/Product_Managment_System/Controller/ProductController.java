package com.example.Product_Managment_System.Controller;

import com.example.Product_Managment_System.DTO.ProductDto;
import com.example.Product_Managment_System.DTO.ProductResponse;
import com.example.Product_Managment_System.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Save a new product.
     * @param productDto The product details received in the request body.
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping("/save-Product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        try {
            Boolean saveProduct = productService.saveProduct(productDto);
            if (!saveProduct) {
                return new ResponseEntity<>("Product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Saved successfully", HttpStatus.CREATED);
    }

    /**
     * Retrieve all products.
     * @return List of all products or an appropriate response if no products are found.
     */
    @GetMapping("/Product")
    public ResponseEntity<?> getProducts() {
        List<ProductDto> allProducts = null;
        try {
            allProducts = productService.getAllProducts(); // Changed from `saveProduct` to `getAllProducts`
            if (CollectionUtils.isEmpty(allProducts)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    /**
     * Retrieve a product by its ID.
     * @param id The ID of the product to retrieve.
     * @return Product details if found, or an appropriate error message.
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id) {
        ProductDto product = null;
        try {
            product = productService.getProductById(id);
            if (ObjectUtils.isEmpty(product)) {
                return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Integer id) {
        Boolean deleteProduct = null;
        try {
            deleteProduct  = productService.deleteProduct(id);
            if (!deleteProduct ) {
                return new ResponseEntity<>("Product not deleted " + id, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Delete sucess", HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getProductsPaginate(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                                 @RequestParam(name = "pageSize",defaultValue = "2")int pageSize,
                                                 @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                                 @RequestParam(name = "sortDir",defaultValue = "asc")String sortDir) {

        ProductResponse productResponse=null;
        try {
            String name = null;
            name.toUpperCase();
          productResponse = productService.getProductsWithPagination(pageNo,pageSize,sortBy,sortDir);
          if(ObjectUtils.isEmpty(productResponse)){
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
