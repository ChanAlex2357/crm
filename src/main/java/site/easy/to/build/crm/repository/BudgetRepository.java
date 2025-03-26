package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.dto.BudgetEtatDTO;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    List<Budget> findByCustomerCustomerId(int customerId);

    @Query(value = "SELECT customer_id as customerId, entree, sortie, reste FROM v_etat_budget", nativeQuery = true)
    List<BudgetEtatDTO> findAllBudgetEtats();
    
    
    @Query(value = "SELECT customer_id as customerId, entree, sortie, reste FROM v_etat_budget WHERE customer_id = :id", nativeQuery = true)
    List<BudgetEtatDTO> findAllBudgetEtatsByCustomer(int id);



}
