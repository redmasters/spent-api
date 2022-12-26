package io.red.spent.controllers.responses;

import java.util.UUID;

public record ExpenseResponse(
        UUID expenseId,
        String namePerson,
        String description,
        String dateTime,
        Double amount
) {
}
