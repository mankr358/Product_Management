package com.example.Product_Managment_System.Repository;

import com.example.Product_Managment_System.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
