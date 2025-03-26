package site.easy.to.build.crm.entity.dto;

public class DashboardData {

    private int totalCustomers;
    private ExpenseTotalDTO totalExpenses;
    public int getTotalCustomers() {
        return totalCustomers;
    }
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    public ExpenseTotalDTO getTotalExpenses() {
        return totalExpenses;
    }
    public void setTotalExpenses(ExpenseTotalDTO totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    
}