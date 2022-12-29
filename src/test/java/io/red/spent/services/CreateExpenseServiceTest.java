package io.red.spent.services;

import io.red.spent.mocks.ExpenseMock;
import io.red.spent.models.Expense;
import io.red.spent.models.ExpenseTag;
import io.red.spent.repositories.ExpenseRepository;
import io.red.spent.repositories.ExpenseTagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class CreateExpenseServiceTest {

    @Mock
    ExpenseRepository repository;
    @Mock
    ExpenseTagRepository expenseTagRepository;
    @InjectMocks
    CreateExpenseService service;

    @Test
    @DisplayName("Should create a valid expense")
    void shoudCreateAValidExpense() {
        //scenery
        final var request = ExpenseMock.toRequest();
        final var expenseModel = ExpenseMock.toEntity();
        when(repository.save(any(Expense.class)))
                .thenReturn(expenseModel);

        final var tags = List.of(ExpenseMock.TAG_NAME, ExpenseMock.TAG_NAME);
        tags.forEach(tag -> {
            when(expenseTagRepository.save(any(ExpenseTag.class)))
                    .thenReturn(ExpenseMock.toExpenseTag());
        });

        //action
        final var savedExpense = service.createExpense(request);
        //assertion
        assertNotNull(savedExpense);
    }

}