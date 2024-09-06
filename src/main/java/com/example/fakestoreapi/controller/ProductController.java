package com.example.fakestoreapi.controller;

import com.example.fakestoreapi.domain.Product;
import com.example.fakestoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping // 경로 없이 사용하는 경우, 기본적으로 부모 경로(/products)에 매핑. ({"", "/"}) 와 동일
//    public String getAllProducts() { return "All products"; }
    public Page<Product> getProducts(@RequestParam(required = false, defaultValue = "0") Long categoryId, @RequestParam(required = false, defaultValue = "0") int page) {
        int size = 10;
        if(categoryId == 0)
            return productService.getProducts(page, size);
        else
            return productService.getProducts(categoryId, page, size);
    }

    @GetMapping("/{id}")
    public Product getProducts(@PathVariable Long id) { // URL 경로에 포함된 변수를 매핑
        return productService.getProduct(id);
    }
}
