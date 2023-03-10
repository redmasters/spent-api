package io.red.spent.services;

import io.red.spent.controllers.exceptions.ExpenseException;
import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.repositories.ExpenseRepository;
import io.red.spent.repositories.query.TagCustommRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ListExpensesService {
    private final ExpenseRepository repository;
    private final TagCustommRepository tagRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListExpensesService.class);

    public ListExpensesService(ExpenseRepository repository, TagCustommRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }


    public Page<ExpenseResponse> listAll(Pageable page) {
        LOGGER.info("Searching all expenses");

        List<ExpenseResponse> expenseReponse = new ArrayList<>();

        final var listExpense = repository.findAllByDeleted(false, page);
        LOGGER.info("Found {} expenses", listExpense.getContent().size());

        listExpense.forEach(expense -> {
            List<String> tags = getTags(expense.getId());

            expenseReponse.add(new ExpenseResponse(
                    expense.getId(),
                    expense.getNamePerson(),
                    expense.getDescription(),
                    expense.getDateTime().toString(),
                    expense.getAmount(),
                    tags
            ));
        });

        return new PageImpl<>(expenseReponse, page, listExpense.getSize());

    }


    public ExpenseResponse listBy(UUID id) {
        LOGGER.info("Searching expense by id {}", id);
        final var expense = repository.findByIdAndDeleted(id, false)
                .orElseThrow(() -> new ExpenseException("Expense not found"));

        List<String> tags = getTags(id);

        LOGGER.info("Found expense of {}", expense.getNamePerson());
        return new ExpenseResponse(
                expense.getId(),
                expense.getNamePerson(),
                expense.getDescription(),
                expense.getDateTime().toString(),
                expense.getAmount(),
                tags
        );
    }

    private List<String> getTags(UUID expense) {
        List<String> tags = new ArrayList<>();

        final var tagsExpense = tagRepository
                .findAll(expense);

        tagsExpense.forEach(tag -> tags.add(
                tag.getTagDescription()));
        return tags;
    }
}
