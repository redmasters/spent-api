package io.red.spent.services;

import io.red.spent.mocks.ExpenseMock;
import io.red.spent.models.Expense;
import io.red.spent.repositories.ExpenseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UpdateExpenseServiceTest {
    @Mock
    ExpenseRepository repository;
    @InjectMocks
    UpdateExpenseService service;

    @Test
    @DisplayName("Should throw exception when expense not found")
    void shouldThrowExceptionWhenExpenseNotFound() {
        final var expense = ExpenseMock.toEntity();
        Expense expenseMock = mock(Expense.class);
        final var requestBody = ExpenseMock.toRequest();

        final var updatedExpense = new Expense(
                expense.getId(),
                "Updated Name",
                requestBody.description(),
                LocalDateTime.parse(requestBody.dateTime()),
                requestBody.amount()
        );

        when(repository.save(any(Expense.class)))
                .thenReturn(updatedExpense);

        assertThrows(RuntimeException.class, () -> service.updateExpense(requestBody, expense.getId()));
    }
}