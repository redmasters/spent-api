package io.red.spent.controllers.requests;

import io.red.spent.services.CreateExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
public class CreateExpenseController {

    private final CreateExpenseService createExpenseService;

    public CreateExpenseController(CreateExpenseService createExpenseService) {
        this.createExpenseService = createExpenseService;
    }

    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody @Validated ExpenseRequest request) {
        return createExpenseService.createExpense(request);
    }
}
