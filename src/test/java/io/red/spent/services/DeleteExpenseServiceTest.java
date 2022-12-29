package io.red.spent.services;

import io.red.spent.mocks.ExpenseMock;
import io.red.spent.models.Expense;
import io.red.spent.repositories.ExpenseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class DeleteExpenseServiceTest {

    @Mock
    ExpenseRepository repository;

    @InjectMocks
    DeleteExpenseService service;

    @Test
    @DisplayName("Should delete an expense")
    void shouldDeleteAnExpense() {
        Expense expense = ExpenseMock.toEntity();

        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(expense));

        when(repository.save(any(Expense.class)))
                .thenReturn(expense);

        final var expenseToDelete = ExpenseMock.toEntity();
        ResponseEntity<String> deletedExpense = service.deleteLogicBy(expenseToDelete.getId());
        assertThat(deletedExpense.getStatusCode().value()).isEqualTo(200);
        assertThat(expense.isDeleted()).isTrue();


    }
}