package io.ugurh.kops.controller;

import io.ugurh.kops.dto.ProductDto;
import io.ugurh.kops.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Transactional
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public @ResponseBody
    List<ProductDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{productId}")
    public @ResponseBody
    ProductDto findById(@PathVariable("productId") Long productId) {
        return service.findById(productId);
    }

    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody ProductDto product) {
        service.create(product);
    }

    @PostMapping("/remove/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("productId") Long productId) {
        service.remove(productId);
    }

    @PostMapping("/edit")
    @ResponseBody
    public void edit(@RequestBody ProductDto product) {
        service.update(product);
    }
}
