package com.example.Product_Managment_System.DTO;

import java.util.List;

public class ProductResponse {
    private List<ProductDto> products;
    private long totalElements;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private int pageNo;
    private int pageSize;

    private ProductResponse(Builder builder) {
        this.products = builder.products;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
        this.isFirst = builder.isFirst;
        this.isLast = builder.isLast;
        this.pageNo = builder.pageNo;
        this.pageSize = builder.pageSize;
    }

    public static Builder builder() {
        return new Builder(); // Corrected to return a new Builder instance
    }

    public static class Builder {
        private List<ProductDto> products;
        private long totalElements;
        private int totalPages;
        private boolean isFirst;
        private boolean isLast;
        private int pageNo;
        private int pageSize;

        public Builder products(List<ProductDto> products) {
            this.products = products;
            return this;
        }

        public Builder totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder isFirst(boolean isFirst) {
            this.isFirst = isFirst;
            return this;
        }

        public Builder isLast(boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public Builder pageNo(int pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(this);
        }
    }
}
