package io.red.spent.mocks;

import io.red.spent.controllers.requests.ExpenseRequest;
import io.red.spent.controllers.responses.ExpenseResponse;
import io.red.spent.models.Expense;
import io.red.spent.models.ExpenseTag;
import io.red.spent.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ExpenseMock {
    public static final String NAME_PERSON = "Denji";
    public static final String DESCRIPTION = "A description";
    public static final String DATE_TIME = "2021-01-01T00:00:00";
    public static final Double AMOUNT = 100.0;
    public static final Long TAG_ID = 1L;
    public static final String TAG_NAME = "Food";

    public static Expense toEntity() {
        return new Expense(
                UUID.randomUUID(),
                NAME_PERSON,
                DESCRIPTION,
                LocalDateTime.parse(DATE_TIME),
                AMOUNT);
    }


    public static ExpenseRequest toRequest() {
        return new ExpenseRequest(
                NAME_PERSON,
                DESCRIPTION,
                DATE_TIME,
                AMOUNT,
                List.of(TAG_NAME, TAG_NAME));
    }

    public static Expense expenseCreated() {
        return new Expense(
                UUID.randomUUID(),
                NAME_PERSON,
                DESCRIPTION,
                LocalDateTime.parse(DATE_TIME),
                AMOUNT);
    }

    public static ResponseEntity<String> responseEntity() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense created");
    }

    public static ExpenseResponse toResponse() {
        return new ExpenseResponse(
                UUID.randomUUID(),
                NAME_PERSON,
                DESCRIPTION,
                DATE_TIME,
                AMOUNT,
                List.of(TAG_NAME, TAG_NAME));
    }

    public static List<ExpenseResponse> toListResponse() {
        List<Expense> expenseList = Arrays.asList(toEntity());
        List<ExpenseResponse> expenseResponseList = new ArrayList<>();
        expenseList.forEach(expense -> {

            expenseResponseList.add(new ExpenseResponse(
                    expense.getId(),
                    expense.getNamePerson(),
                    expense.getDescription(),
                    expense.getDateTime().toString(),
                    expense.getAmount(),
                    List.of(TAG_NAME)
            ));
        });
        return expenseResponseList;
    }

    public static List<Expense> toListEntity() {
        return List.of(toEntity());
    }

    public static Page<ExpenseResponse> toPageResponse(Pageable page) {
        final var expenseList = toListResponse();
        List<ExpenseResponse> expenseResponseList = new ArrayList<>();
        expenseResponseList.add(toResponse());
        return new PageImpl<>(expenseResponseList, page, expenseList.size());
    }

    public static Page<Expense> toPage(Pageable page) {
        final var expenseList = toListEntity();
        return new PageImpl<>(expenseList, page, expenseList.size());

    }

    public static Tag toTag() {
        return new Tag(TAG_ID, TAG_NAME);
    }
    public static ExpenseTag toExpenseTag() {
        return new ExpenseTag(
                toEntity(),
                toTag()
        );
    }
}
