package com.estudos.estoques.stockMovement.api;

import com.estudos.estoques.stockMovement.application.CreateEntryUseCase;
import com.estudos.estoques.stockMovement.application.CreateExitUseCase;
import com.estudos.estoques.stockMovement.application.CreateMovementCommand;
import com.estudos.estoques.stockMovement.application.FindByIdMovementUseCase;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementEntity;
import com.estudos.estoques.stockMovement.infrastructure.StockMovementMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock-movement")
public class StockMovementController {

  private final CreateEntryUseCase createEntryUseCase;
  private final CreateExitUseCase createExitUseCase;
  private final FindByIdMovementUseCase findByIdMovementUseCase;

  public StockMovementController(
    CreateEntryUseCase createEntryUseCase,
    CreateExitUseCase createExitUseCase,
    FindByIdMovementUseCase findByIdMovementUseCase
  ) {
    this.createEntryUseCase = createEntryUseCase;
    this.createExitUseCase = createExitUseCase;
    this.findByIdMovementUseCase = findByIdMovementUseCase;
  }

  @PostMapping("create-entry")
  public ResponseEntity<String> createEntry(
    @RequestBody CreateMovementRequest request
  ) {
    try {
      CreateMovementCommand command = new CreateMovementCommand(
        request.getQuantity(),
        request.getProduct(),
        request.getOcurredAt()
      );
      String response = createEntryUseCase.execute(command);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("create-exit")
  public ResponseEntity<String> createExit(
    @RequestBody CreateMovementRequest request
  ) {
    try {
      CreateMovementCommand command = new CreateMovementCommand(
        request.getQuantity(),
        request.getProduct(),
        request.getOcurredAt()
      );
      String response = createExitUseCase.execute(command);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("findById/{id}")
  public ResponseEntity<FindByIdMovementResponse> findById(
    @PathVariable Long id
  ) {
    StockMovementEntity movementEntity = findByIdMovementUseCase.execute(id);
    FindByIdMovementResponse response = FindByIdMovementResponse.fromDomain(
      StockMovementMapper.toDomain(movementEntity)
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
