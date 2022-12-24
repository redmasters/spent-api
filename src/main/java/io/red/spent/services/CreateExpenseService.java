package io.red.spent.services;

import io.red.spent.controllers.requests.ExpenseRequest;
import io.red.spent.models.Expense;
import io.red.spent.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateExpenseService {
    private final ExpenseRepository expenseRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateExpenseService.class);

    public CreateExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ResponseEntity<String> createExpense(ExpenseRequest request) {

        final var newExpense = new Expense(
                request.namePerson(),
                request.description(),
                LocalDateTime.parse(request.dateTime()),
                request.amount()
        );
        expenseRepository.save(newExpense);

        LOGGER.info("Expense for {} created", request.namePerson());
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense created");
    }
}
