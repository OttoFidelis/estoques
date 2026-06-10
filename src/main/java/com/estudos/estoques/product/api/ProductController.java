package com.estudos.estoques.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.estoques.product.application.CreateProductCommand;
import com.estudos.estoques.product.application.CreateProductUseCase;
import com.estudos.estoques.product.application.FindByIdProductUseCase;
import com.estudos.estoques.product.domain.Product;
import com.estudos.estoques.product.infrastructure.ProductEntity;
import com.estudos.estoques.product.infrastructure.ProductMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("product")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final FindByIdProductUseCase findByIdProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, FindByIdProductUseCase findByIdProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.findByIdProductUseCase = findByIdProductUseCase;
    }

    @PostMapping("create")
    public ResponseEntity<CreateProductResponse> create(@RequestBody CreateProductRequest request) {
        try {
            CreateProductCommand command = new CreateProductCommand(
                    request.getName(),
                    request.getDescription(),
                    request.getSku(),
                    request.getQuantity(),
                    request.getMinQuantity());
            Product product = createProductUseCase.execute(command);
            return new ResponseEntity<CreateProductResponse>(CreateProductResponse.fromDomain(product), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<FindByIdProductResponse> findById(@PathVariable Long id) {
        ProductEntity productEntity = findByIdProductUseCase.execute(id);
        return new ResponseEntity<FindByIdProductResponse>(FindByIdProductResponse.fromDomain(ProductMapper.toDomain(productEntity)), HttpStatus.OK);
    }
    
}
