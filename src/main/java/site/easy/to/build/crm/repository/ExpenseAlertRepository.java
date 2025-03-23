package site.easy.to.build.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.ExpenseAlert;

@Repository
public interface ExpenseAlertRepository extends JpaRepository<ExpenseAlert, Integer> {
    // ...existing code if needed...
}
