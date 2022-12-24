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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ListExpensesServicesTest {

    @Mock
    ExpenseRepository repository;

    @InjectMocks
    ListExpensesServices services;

    @Test
    @DisplayName("Should return a list of all expenses")
    void shouldReturnListOfAllExpenses() {
        final var listExpense = ExpenseMock.toListResponse();
        final var litEntitty = ExpenseMock.toListEntity();
        when(repository.findAll())
                .thenReturn(litEntitty);

        List<ExpenseResponse> responseList = services.listAll();
        Assertions.assertFalse(responseList.isEmpty());
    }
}