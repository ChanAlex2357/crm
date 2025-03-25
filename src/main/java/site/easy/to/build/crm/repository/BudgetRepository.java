package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    List<Budget> findByCustomerCustomerId(int customerId);
}
