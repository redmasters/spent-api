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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UpdateExpenseServiceTest {
    @Mock
    ExpenseRepository repository;
    @InjectMocks
    UpdateExpenseService service;

    @Test
    @DisplayName("Should update an expense")
    void shouldUpdateAnExpense() {
        final var expense = ExpenseMock.toEntity();
        final var requestBody = ExpenseMock.toRequest();

        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(expense));

        final var updatedExpense = new Expense(
                expense.getId(),
                requestBody.namePerson(),
                requestBody.description(),
                LocalDateTime.parse(requestBody.dateTime()),
                requestBody.amount()
        );

        when(repository.save(updatedExpense))
                .thenReturn(updatedExpense);

        final var serviceResponse = service.updateExpense(requestBody, expense.getId());
        assertThat(expense.getId()).isEqualTo(updatedExpense.getId());
        assertThat(serviceResponse.getBody()).isEqualTo("Expense updated");
        assertThat(serviceResponse.getStatusCodeValue()).isEqualTo(204);
    }
}