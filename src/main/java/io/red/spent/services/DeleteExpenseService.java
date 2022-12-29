package io.red.spent.services;

import io.red.spent.controllers.exceptions.BusinessException;
import io.red.spent.controllers.exceptions.ExpenseException;
import io.red.spent.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteExpenseService {
    private final ExpenseRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteExpenseService.class);

    public DeleteExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> deleteLogicBy(UUID id) {
        LOGGER.info("Deleting expense with id {}", id);
        final var expense = repository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        if(expense.isDeleted()){
            throw new BusinessException("Expense already deleted");
        }

        expense.delete();

        repository.save(expense);
        return ResponseEntity.status(200).body("Expense deleted");

    }

    public void deleteExpenseBy(UUID id) {
        LOGGER.info("Deleting expense with id {}", id);
        final var expense = repository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        repository.delete(expense);
        LOGGER.info("Expense Deleted");
    }
}
