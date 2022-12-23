package io.red.spent.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sa_expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private UUID id;
    @Column(name = "expense_person")
    private String namePerson;
    @Column(name = "expense_description")
    private String description;
    @Column(name = "expense_date_time")
    private LocalDateTime dateTime;
    @Column(name = "expense_amount")
    private Double amount;
    public Expense() {
    }

    public UUID getId() {
        return id;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getAmount() {
        return amount;
    }
}
