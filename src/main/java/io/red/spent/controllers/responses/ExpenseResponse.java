package io.red.spent.controllers.responses;

import java.util.List;
import java.util.UUID;

public record ExpenseResponse(
        UUID expenseId,
        String namePerson,
        String description,
        String dateTime,
        Double amount,
        List<String>tag
) {
}
