package io.red.spent.controllers;

import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.services.ListExpensesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/expense")
@RestController
public class ListExpensesController {
    private final ListExpensesService services;

    public ListExpensesController(ListExpensesService services) {
        this.services = services;
    }

    @GetMapping("/all")
    public Page<ExpenseResponse> listAllExpenses(Pageable pages) {
        return services.listAll(pages);
    }

}
