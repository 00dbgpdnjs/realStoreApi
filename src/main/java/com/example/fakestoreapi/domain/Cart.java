package com.example.fakestoreapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String date; // yyyymmdd

    @JsonManagedReference // 부모 엔티티에서 자식 엔티티로의 관계를 관리 -> JSON 직렬화
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    // mappedBy: cartItems가 CartItem 엔티티의 cart 필드에 의해 매핑
    // ; CartItem 엔티티의 cart 필드가 Cart 엔티티와의 관계를 관리
    private List<CartItem> cartItems = new ArrayList<>();
}
