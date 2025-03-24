package site.easy.to.build.crm.entity.dto;

import java.time.LocalDate;

public interface ExpenseTotalDTO {
    // Basic expense information
    Integer getExpenseId();
    LocalDate getDateExpense();
    Double getAmount();
    
    // Customer information
    Integer getCustomerId();
    String getCustomerName();
    String getCustomerEmail();
    
    // Lead information 
    Integer getLeadId();
    String getLeadName();
    String getLeadStatus();
    
    // Ticket information
    Integer getTicketId();
    String getTicketSubject();
    String getTicketStatus();
    String getTicketPriority();
    
    // Budget information
    Integer getBudgetId();
    Double getBudgetAmount();
    // Date getBudgetStartDate();
    // Date getBudgetEndDate();
    
    // Currency information
    Integer getCurrencyId();
    String getCurrencyName();
    String getCurrencyValue();
}
