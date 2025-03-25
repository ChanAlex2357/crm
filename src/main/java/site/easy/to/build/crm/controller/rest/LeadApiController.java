package site.easy.to.build.crm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.api.request.ModifyExpenseData;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.service.expense.ExpenseService;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/leads")
@Slf4j
public class LeadApiController {
    
    @Autowired
    private LeadServiceImpl leadService;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLeadById(@PathVariable int id) {
        try {
            var leads = leadService.getLeadCpl(id);
            ApiResponse apiResponse = ApiResponseBuilder.success("Lead", leads);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting leads", e));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getLeadSummary() {
        try {
            var leads = leadService.getAllLeadsCpl();
            ApiResponse apiResponse = ApiResponseBuilder.success("Total des leads", leads);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting leads", e));
        }
    }

    @DeleteMapping("/{leadId}")
    public ResponseEntity<ApiResponse> delteLead(@PathVariable int leadId){
        log.info("Deleting lead with id {}",leadId);

        Lead lead = leadService.findByLeadId(leadId);
        leadService.delete(lead);
        return ResponseEntity.ok(ApiResponseBuilder.success("Lead deleted",true));
    }


    @PostMapping("/{leadId}")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody ModifyExpenseData data, @PathVariable int leadId) {
        
        Lead currentLead = leadService.findByLeadId(leadId);
        if (currentLead.getLeadId() != data.getLeadId()) {
            return ResponseEntity.ok().body(ApiResponseBuilder.success("Lead not found",false));
        }
        Expense e = expenseService.getExpenseByLead(leadId).get(0);
        e.setAmount(data.getExpense());
        expenseService.updateExpense(e);
        return ResponseEntity.ok(ApiResponseBuilder.success("Lead updated",true));
    }
}

