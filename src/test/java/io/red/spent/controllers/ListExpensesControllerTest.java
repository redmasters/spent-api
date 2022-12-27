package io.red.spent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.red.spent.mocks.ExpenseMock;
import io.red.spent.services.ListExpensesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ListExpensesControllerTest {

    static final String BASE_URL = "/v1/expense";
    ObjectMapper mapper;

    MockMvc mockMvc;
    @Mock
    ListExpensesService service;
    @InjectMocks
    ListExpensesController controller;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ListExpensesController(service))
                .build();
    }

    @Test
    @DisplayName("Should return status 200 and an expense")
    void shouldReturn200AndExpense() throws Exception {
        final var expense = ExpenseMock.toEntity();
        final var expenseResponse = ExpenseMock.toResponse();
        when(service.listBy(any(UUID.class)))
                .thenReturn(expenseResponse);

        final var controllerResponse = controller.findBy(UUID.randomUUID());

        mockMvc.perform(get(BASE_URL + "/?expenseId=" + expense.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath(
//                        "$.expenseId")
//                        .value(expense.getId().toString()));

        assertNotNull(controllerResponse);

    }
}