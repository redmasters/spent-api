package io.red.spent.controllers;

import io.red.spent.controllers.requests.ExpenseRequest;
import io.red.spent.services.CreateExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/expense")
public class CreateExpenseController {

    private final CreateExpenseService createExpenseService;

    public CreateExpenseController(CreateExpenseService createExpenseService) {
        this.createExpenseService = createExpenseService;
    }

    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody @Valid ExpenseRequest request) {
        return createExpenseService.createExpense(request);
    }
}
