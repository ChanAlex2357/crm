package site.easy.to.build.crm.service.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.easy.to.build.crm.entity.AlertType;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.ExpenseAlert;
import site.easy.to.build.crm.repository.ExpenseAlertRepository;

@Service
public class ExpenseAlertService {

    @Autowired
    private ExpenseAlertRepository expenseAlertRepository;

    public List<ExpenseAlert> getAllExpenseAlerts() {
        return expenseAlertRepository.findAll();
    }

    public ExpenseAlert getExpenseAlertById(int id) {
        return expenseAlertRepository.findById(id).orElse(null);
    }
    @Transactional
    public ExpenseAlert createExpenseAlert(ExpenseAlert expenseAlert) {
        return expenseAlertRepository.save(expenseAlert);
    }

    public ExpenseAlert updateExpenseAlert(ExpenseAlert expenseAlert) {
        return expenseAlertRepository.save(expenseAlert);
    }

    public void deleteExpenseAlert(int id) {
        expenseAlertRepository.deleteById(id);
    }


    public ExpenseAlert getExpenseAlert(Expense expense,BigDecimal taux , AlertType alertType , String message){
        ExpenseAlert expenseAlert = new ExpenseAlert();
        expenseAlert.setBudget(expense.getBudget());
        expenseAlert.setExpense(expense);
        expenseAlert.setDateAlert(LocalDate.now());
        expenseAlert.setBudgetAmount(expense.getBudget().getAmount());
        expenseAlert.setExpenseAmount(expense.getAmount());
        expenseAlert.setStatus(10);
        expenseAlert.setAlertType(alertType);
        expenseAlert.setTaux((taux));
        if (message != null) {
            expenseAlert.setMessage(message);
        }
        else {
            expenseAlert.setMessage(alertType.getDesce());
        }
        return expenseAlert;

    }
}
