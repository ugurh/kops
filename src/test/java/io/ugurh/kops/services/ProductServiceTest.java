package io.ugurh.kops.services;


import io.ugurh.kops.dto.ProductDto;
import io.ugurh.kops.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    void testFindAllProduct() {
        assertEquals(0L, service.findAll().size());
    }

    @Test
    void testCreateProduct() {
        ProductDto product1 = new ProductDto();
        product1.setName("Spring in Action Book");
        service.create(product1);

        ProductDto product2 = new ProductDto();
        product2.setName("Hibernate in Action Book");
        service.create(product2);

        assertEquals(2L, service.findAll().size());
    }

    @Test
    void testFindProductById() {
        ProductDto product = new ProductDto();
        product.setName("Spring in Action Book");
        service.create(product);

        List<ProductDto> pList = service.findAll();
        ProductDto productInDB = pList.get(0);

        ProductDto productDto = service.findById(productInDB.getId());
        assertEquals("Spring in Action Book", productDto.getName());
    }

    @Test
    void testRemoveProduct() {
        ProductDto product1 = new ProductDto();
        product1.setName("Spring in Action Book");
        service.create(product1);

        ProductDto product2 = new ProductDto();
        product2.setName("Hibernate in Action Book");
        service.create(product2);

        assertEquals(2L, service.findAll().size());

        List<ProductDto> pList = service.findAll();
        ProductDto product = pList.get(0);
        service.remove(product.getId());

        assertEquals(1L, service.findAll().size());
    }

    @Test
    void testUpdateProduct() {
        ProductDto product1 = new ProductDto();
        product1.setName("Spring in Action Book");
        service.create(product1);
        assertEquals(1L, service.findAll().size());
        List<ProductDto> pList = service.findAll();
        ProductDto product = pList.get(0);
        product.setName("Head First Design Patterns");
        service.update(product);
        List<ProductDto> pList2 = service.findAll();
        ProductDto product2 = pList2.get(0);
        assertEquals("Head First Design Patterns", product2.getName());
    }

}
