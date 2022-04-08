package io.ugurh.kops.service;

import io.ugurh.kops.dto.ProductDto;
import io.ugurh.kops.entity.Product;
import io.ugurh.kops.mapper.ProductMapper;
import io.ugurh.kops.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(productMapper.mapEntityToDto(product));
        }
        return productDtos;
    }

    public void create(ProductDto productDto) {
        productRepository.save(productMapper.mapDtoToEntity(productDto));
    }

    public ProductDto findById(Long productId) {
        Product product = productRepository.getById(productId);
        return productMapper.mapEntityToDto(product);
    }

    public void remove(Long productId) {
        productRepository.findById(productId)
                .ifPresent(productRepository::delete);
    }

    public void update(ProductDto productDto) {
        productRepository.save(productMapper.mapDtoToEntity(productDto));
    }
}
