package io.red.spent.services;

import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.repositories.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListExpensesService {
    private final ExpenseRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListExpensesService.class);

    public ListExpensesService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Page<ExpenseResponse> listAll(Pageable page) {
        LOGGER.info("Searching all expenses");

        List<ExpenseResponse> expenseReponse = new ArrayList<>();

        final var listExpense = repository.findAll(page);
        LOGGER.info("Found {} expenses", listExpense.getSize());

        listExpense.forEach(expense -> {
            expenseReponse.add(new ExpenseResponse(
                    expense.getId(),
                    expense.getNamePerson(),
                    expense.getDescription(),
                    expense.getDateTime().toString(),
                    expense.getAmount()
            ));
        });

        return new PageImpl<>(expenseReponse, page, listExpense.getSize());

    }
}