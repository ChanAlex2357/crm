package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.dto.ExpenseTotalDTO;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    

    @Query(value = """ 
            Select 
                expense_id as expenseId, 
                date_expense as dateExpense,
                amount as amount,

                customer_id as customerId,
                customer_name as customerName,
                customer_email as customerEmail,
                
                lead_id as leadId,
                lead_name as leadName,
                lead_status as leadStatus,
                
                ticket_id as ticketId,
                ticket_subject as ticketSubject,
                ticket_status as ticketStatus,
                ticket_priority as ticketPriority
            from customer_expense_cpl ce
            """
    , nativeQuery = true) 
    List<ExpenseTotalDTO> findAllExpensesWithDetails();
    
    @Query(value = "SELECT * FROM customer_expense_cpl c WHERE c.customerId = :customerId" , nativeQuery = true)
    List<ExpenseTotalDTO> findExpensesByCustomerId(Integer customerId);

    List<Expense> findByLeadLeadId(int leadId);
    List<Expense> findByTicketTicketId(int ticketId);

}
