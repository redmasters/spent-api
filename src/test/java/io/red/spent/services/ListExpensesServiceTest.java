package io.red.spent.services;

import io.red.spent.controllers.exceptions.ExpenseException;
import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.mocks.ExpenseMock;
import io.red.spent.models.Expense;
import io.red.spent.models.dao.TagDAO;
import io.red.spent.repositories.ExpenseRepository;
import io.red.spent.repositories.query.TagCustommRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ListExpensesServiceTest {

    @Mock
    ExpenseRepository repository;
    @Mock
    TagCustommRepository tagRepository;

    @InjectMocks
    ListExpensesService services;

    @Test
    @DisplayName("Should return a paginated list of all expenses")
    void shouldReturnListOfAllExpenses() {
        PageRequest pageable = mock(PageRequest.class);

        List<Expense> responseList = List.of(ExpenseMock.toEntity());
        List<TagDAO> tagList = List.of(
                new TagDAO(
                1L,
                "Desc"
                ),
                new TagDAO(
                        2L,
                        "Desc"
                )
        );


        Page<Expense> expensePage = new PageImpl<>(
                responseList, pageable, responseList.size());

        when(repository.findAllByDeleted(any(Boolean.class), any(Pageable.class)))
                .thenReturn(expensePage);

        when(tagRepository.findAll(any(UUID.class)))
                .thenReturn(tagList);

        Page<ExpenseResponse> responses = services.listAll(pageable);
        assertFalse(responses.isEmpty());
    }

    @Test
    @DisplayName("Shoud throw exception expense by UUID")
    void shouldThrowExcpetion() {

        final var expense = ExpenseMock.toEntity();
        ReflectionTestUtils.setField(expense, "id", UUID.randomUUID());

        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(expense));

        assertThrows(ExpenseException.class, () -> services.listBy(expense.getId()));

    }
}