package site.easy.to.build.crm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.TotalExpense;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.entity.dto.ExpenseTotalDTO;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.expense.ExpenseService;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseApiController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse> getExpenseSummary() {
        try {
            List<ExpenseTotalDTO> expenses = expenseService.getAllExpensesWithDetails();
            TotalExpense totalExpense = new TotalExpense(expenses);
            totalExpense.setTotalCustomers(customerService.findAll().size());

            ApiResponse apiResponse = ApiResponseBuilder.success("Total de depenses",totalExpense);
    
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting expenses", e));
        }
    }
}
