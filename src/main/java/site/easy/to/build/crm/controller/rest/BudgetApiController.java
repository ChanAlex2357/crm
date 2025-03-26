package site.easy.to.build.crm.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.entity.dto.BudgetEtatDTO;
import site.easy.to.build.crm.service.budget.BudgetService;

@RestController
@RequestMapping("/api/budgets")
@Slf4j
public class BudgetApiController {
    
    @Autowired
    BudgetService   budgetService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getBudgeByCustomer(@PathVariable int customerId){
        try {
            List<BudgetEtatDTO> budgets = budgetService.getAllBudgetEtatsByCustomer(customerId);
            return  ResponseEntity.ok().body( ApiResponseBuilder.success("budget geted", budgets.get(0)));
            
        } catch (Exception e) {
            log.error("Error while getting budget", e);
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting budget", e.getMessage()));
        }
    }

     @GetMapping
    public ResponseEntity<ApiResponse> getAllBudgetEtats(Model model) {
        List<BudgetEtatDTO> budgets = budgetService.getAllBudgetEtats();
        return  ResponseEntity.ok().body( ApiResponseBuilder.success("budget geted", budgets));
    }

    // @GetMapping("/{customerId}")
    // public String getAllBudgetEtatsByCustomer(@PathVariable int customerId, Model model) {
    //     model.addAttribute("budgets", budgetService.getAllBudgetEtatsByCustomer(customerId));
    //     return "budgets";
    // }
}
