package io.red.spent.models;


import javax.persistence.*;

@Entity
@Table(name = "sa_expense_tag")
public class ExpenseTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "expense_tag_id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "fksa_expense_tagexpense_id")
    public Expense expense;

    @ManyToOne
    @JoinColumn(name = "fksa_expense_tagtag_id")
    public Tag tag;

    public ExpenseTag() {
    }

    public ExpenseTag(Long id, Expense expense, Tag tag) {
        this.id = id;
        this.expense = expense;
        this.tag = tag;
    }

    public ExpenseTag(Expense expense, Tag tag) {
        this.expense = expense;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public Expense getExpense() {
        return expense;
    }

    public Tag getTag() {
        return tag;
    }
}
