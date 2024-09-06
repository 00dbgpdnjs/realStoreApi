package com.example.fakestoreapi.controller;

import com.example.fakestoreapi.domain.Product;
import com.example.fakestoreapi.dto.AddProductDto;
import com.example.fakestoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')") // TODO : security
    public Product addProduct(@RequestBody AddProductDto addProductDto) {
        return productService.addProduct(addProductDto);
    }

    @GetMapping // 경로 없이 사용하는 경우, 기본적으로 부모 경로(/products)에 매핑. ({"", "/"}) 와 동일
//    public String getAllProducts() { return "All products"; }
    public Page<Product> getProducts(@RequestParam(required = false, defaultValue = "0") Long categoryId, @RequestParam(required = false, defaultValue = "0") int page) {
        int size = 10;
        if(categoryId == 0)
            return productService.getProducts(page, size); // TODO : improve
            /* Category select문 4번 출력됨
                N+1 문제
                각 Product에 연결된 Category를 조회하기 위해 추가로 N번의 쿼리가 발생
                현재 Product에 총 4개의 카테고리가 있음
             */
        else
            return productService.getProducts(categoryId, page, size);
    }

    @GetMapping("/{id}")
    public Product getProducts(@PathVariable Long id) { // URL 경로에 포함된 변수를 매핑
        return productService.getProduct(id);
    }
}
