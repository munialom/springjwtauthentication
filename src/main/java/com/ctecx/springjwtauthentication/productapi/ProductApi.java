package com.ctecx.springjwtauthentication.productapi;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DenyAll;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductApi {
    private  final ProductRepository productRepository;
    @GetMapping
    public List<Product> productList(){

        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product){

        Product savedProduct=productRepository.save(product);
        URI productURI= URI.create("/products/"+savedProduct.getId());

        return ResponseEntity.created(productURI).body(savedProduct);
    }

}
