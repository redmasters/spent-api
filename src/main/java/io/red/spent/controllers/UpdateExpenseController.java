package io.red.spent.controllers;

import io.red.spent.controllers.requests.ExpenseRequest;
import io.red.spent.services.UpdateExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v1/expense")
@RestController
public class UpdateExpenseController {

    private final UpdateExpenseService service;

    public UpdateExpenseController(UpdateExpenseService service) {
        this.service = service;
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<String> updateExpense(
            @PathVariable(name = "expenseId") UUID id,
            @RequestBody ExpenseRequest request) {
        return service.updateExpense(request, id);
    }
}
