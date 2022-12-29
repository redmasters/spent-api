package io.red.spent.controllers.requests;

import java.util.List;

public record ExpenseRequest(
        String namePerson,
        String description,
        String dateTime,
        Double amount,
        List<String> tagName
) {
    public record Tag(
            String tagName
    ) {
    }
}
