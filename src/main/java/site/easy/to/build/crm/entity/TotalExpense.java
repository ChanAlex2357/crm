package site.easy.to.build.crm.entity;

import java.util.List;

import lombok.Data;
import site.easy.to.build.crm.entity.dto.ExpenseTotalDTO;

@Data
public class TotalExpense {
    private double totalAmount;
    private int totalCount;
    private int totalCustomers;
    private List<ExpenseTotalDTO> expenses;
    
    public TotalExpense(List<ExpenseTotalDTO> expenses){
        this.expenses = expenses;
        this.totalAmount = expenses.stream().mapToDouble(ExpenseTotalDTO::getAmount).sum();
        this.totalCount = expenses.size();
        this.totalCustomers = 0;
    }
}
