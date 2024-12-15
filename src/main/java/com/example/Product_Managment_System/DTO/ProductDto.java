package com.example.Product_Managment_System.DTO;

import jakarta.validation.constraints.*;

public class ProductDto {
    private Integer id;

  //  @NotBlank
    private String name;

//    @NotEmpty
//    @Size(min = 3, max = 10, message = "Description size must be between 3 and 10 characters")
    private String description;

//    @NotNull(message = "Price cannot be null")
//    @Digits(integer = 10, fraction = 2, message = "Invalid price format (up to 10 digits with 2 decimal places)")
    private Double price;

//    @NotNull(message = "Quantity cannot be null")
//    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

  //  @Email(message = "Invalid email format")
    private String email;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
