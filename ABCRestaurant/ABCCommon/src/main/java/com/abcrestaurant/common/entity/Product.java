package com.abcrestaurant.common.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(length = 512)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    public String getPhotosImagePath() {
        if (id == null || image == null) return "/images/default-product.png";
        return "/images/product-images/" + this.id + "/" + this.image;
    }
}