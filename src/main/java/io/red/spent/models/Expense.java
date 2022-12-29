package io.red.spent.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sa_expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private UUID id;
    @NotBlank(message = "Name may not be blank")
    @Column(name = "expense_person")
    private String namePerson;
    @NotBlank(message = "Description may not be blank")
    @Column(name = "expense_description")
    private String description;
    @Column(name = "expense_date_time")
    private LocalDateTime dateTime;
    @Column(name = "expense_amount")
    private Double amount;

    @Column(name = "expense_deleted")
    private boolean deleted = false;
    public Expense() {
    }

    public Expense(UUID id, String namePerson,
                   String description, LocalDateTime dateTime,
                   Double amount) {
        this.id = id;
        this.namePerson = namePerson;
        this.description = description;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public Expense(String namePerson, String description, LocalDateTime dateTime, Double amount) {
        this.namePerson = namePerson;
        this.description = description;
        this.dateTime = dateTime;
        this.amount = amount;
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

    public boolean isDeleted() {
        return deleted;
    }
    public void delete(){
        this.deleted = true;
    }
}
