package io.red.spent.controllers;

import io.red.spent.services.DeleteExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v1/expense")
@RestController
public class DeleteExpenseController {
    private final DeleteExpenseService service;

    public DeleteExpenseController(DeleteExpenseService service) {
        this.service = service;
    }

    @PatchMapping("/delete/{expenseId}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable(name = "expenseId") UUID id) {
        return service.deleteLogicBy(id);
    }
}
