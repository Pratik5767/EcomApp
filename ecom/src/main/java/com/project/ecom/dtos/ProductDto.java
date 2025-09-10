package com.project.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private String description;
    private byte[] byteImage;
    private Long categoryId;
    private String categoryName;
    private MultipartFile image;
}
