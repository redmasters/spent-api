package io.red.spent.services;

import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.mocks.ExpenseMock;
import io.red.spent.repositories.ExpenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ListExpensesServiceTest {

    @Mock
    ExpenseRepository repository;

    @InjectMocks
    ListExpensesService services;

    @Test
    @DisplayName("Should return a paginated list of all expenses")
    void shouldReturnListOfAllExpenses() {
        Pageable page = Pageable.unpaged();

        final var pageExpense = ExpenseMock.toPage(page);
        when(repository.findAll(page)).thenReturn(pageExpense);

        Page<ExpenseResponse> responseList = services.listAll(page);
        Assertions.assertFalse(responseList.isEmpty());
    }
}