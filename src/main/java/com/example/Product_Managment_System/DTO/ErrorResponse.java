package com.example.Product_Managment_System.DTO;


import lombok.Data;

@Data
public class ErrorResponse {
    private Integer status;
    private String message;

    // Private constructor to prevent direct instantiation
    private ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Static Builder Class
    public static class Builder {
        private String message;
        private int status;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }


        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

    // Static method to initiate the builder
    public static Builder builder() {
        return new Builder();
    }
}



