package io.red.spent.repositories;

import io.red.spent.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByIdAndDeleted(UUID id, boolean deleted);
    Optional<Expense> findById(UUID id);

    Page<Expense> findAllByDeleted(boolean deleted, Pageable pageable);
}
