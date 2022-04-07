package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.ProductDto;
import io.ugurh.kops.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        if (null != productDto.getId()) product.setId(productDto.getId());
        if (null != productDto.getName()) product.setName(productDto.getName());
        return product;
    }

    public ProductDto mapEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (null != product.getId()) productDto.setId(product.getId());
        if (null != product.getName()) productDto.setName(product.getName());
        return productDto;
    }
}

