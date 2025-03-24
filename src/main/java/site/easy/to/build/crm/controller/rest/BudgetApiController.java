package site.easy.to.build.crm.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.service.budget.BudgetService;

@RestController
@RequestMapping("/api/customer/budget")
@Slf4j
public class BudgetApiController {
    
    @Autowired
    BudgetService   budgetService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getBudgeByCustomer(@PathVariable int customerId){
        try {
            List<Budget> budgets = budgetService.findByCustomerId(customerId);
            return  ResponseEntity.ok().body( ApiResponseBuilder.success("budget geted", budgets));
            
        } catch (Exception e) {
            log.error("Error while getting budget", e);
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting budget", e.getMessage()));
        }
    }
}
