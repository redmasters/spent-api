package io.red.spent.services;

import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListExpensesServices {
    private final ExpenseRepository repository;

    public ListExpensesServices(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<ExpenseResponse> listAll(){
       List<ExpenseResponse> expenseReponse = new ArrayList<>();
       final var listExpense = repository.findAll();
         listExpense.forEach(expense -> {
              expenseReponse.add(new ExpenseResponse(
                     expense.getId(),
                     expense.getNamePerson(),
                     expense.getDescription(),
                     expense.getDateTime().toString(),
                     expense.getAmount()
              ));
         });

       return expenseReponse;

    }
}
