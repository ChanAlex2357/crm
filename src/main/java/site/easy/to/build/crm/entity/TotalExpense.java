package site.easy.to.build.crm.entity;

import java.util.List;

import lombok.Data;

@Data
public class TotalExpense {
    private double totalAmount;
    private int totalCount;
    private List<Expense> expenses;
    
    public TotalExpense(List<Expense> expenses){
        this.expenses = expenses;
        this.totalAmount = expenses.stream().mapToDouble(Expense::getDoubleAmount).sum();
        this.totalCount = expenses.size();
    }
}
