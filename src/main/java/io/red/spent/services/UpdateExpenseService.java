package io.red.spent.services;

import io.red.spent.controllers.requests.ExpenseRequest;
import io.red.spent.models.Expense;
import io.red.spent.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UpdateExpenseService {
    private final ExpenseRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateExpenseService.class);


    public UpdateExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> updateExpense(ExpenseRequest request, UUID id) {
        LOGGER.info("Updating expense with id: {}", id);
       final var expense = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("Expense not found"));

       final var updatedExpense = new Expense(
                expense.getId(),
                request.namePerson(),
                request.description(),
                LocalDateTime.now(),
                request.amount()
         );
       LOGGER.info("Saving updated expense with id: {}", id);
       repository.save(updatedExpense);

        return ResponseEntity.status(204).body("Expense updated");
    }


}
