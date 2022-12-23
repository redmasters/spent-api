package io.red.spent.controllers.requests;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record ExpenseRequest(
        @NotBlank
        String namePerson,
        String description,
        String dateTime,
        Double amount,
        List<Tag> tags
) {
    public record Tag(
            String tagName
    ) {
    }
}
