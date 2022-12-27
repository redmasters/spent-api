package io.red.spent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.red.spent.mocks.ExpenseMock;
import io.red.spent.services.CreateExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreateExpenseControllerTest {

    @Mock
    CreateExpenseService service;
    @InjectMocks
    CreateExpenseController controller;
    @MockBean
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    String BASE_URL = "/v1/expense";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CreateExpenseController(service))
                .build();
    }

    @Test
    @DisplayName("Shoud create expense, return 201 and response body")
    void shoudCreateAndReturnExpense() throws Exception {
        final var request = ExpenseMock.toRequest();
        String json = objectMapper.writeValueAsString(request);

        final var newExpense = ExpenseMock.expenseCreated();
        final var entityResponse = ExpenseMock.responseEntity();
        when(service.createExpense(any()))
                .thenReturn(entityResponse);

        final var response = controller.createExpense(request);

        mockMvc.perform(post(BASE_URL)
                        .content(json)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service, atLeastOnce()).createExpense(request);
        assertNotNull(response);

    }
}