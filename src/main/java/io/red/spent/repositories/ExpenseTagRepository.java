package io.red.spent.repositories;

import io.red.spent.models.ExpenseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTagRepository extends JpaRepository<ExpenseTag, Long> {
}
