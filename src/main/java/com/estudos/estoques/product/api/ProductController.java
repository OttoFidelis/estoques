package com.estudos.estoques.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.estoques.product.application.CreateProductCommand;
import com.estudos.estoques.product.application.CreateProductUseCase;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("product")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request){
        CreateProductCommand command = new CreateProductCommand(
            request.getId(),
            request.getName(),
            request.getDescription(),
            request.getSku(),
            request.getQuantity(),
            request.getMinQuantity()
        );
        createProductUseCase.execute(command);
        return ResponseEntity.ok().build();
    }
    
}
