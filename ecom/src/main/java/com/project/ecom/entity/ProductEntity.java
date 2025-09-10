package com.project.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecom.dtos.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CategoryEntity category;

    public ProductDto convertToDto() {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .byteImage(image)
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();
    }
}
