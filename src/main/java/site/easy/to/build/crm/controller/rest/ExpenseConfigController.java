package site.easy.to.build.crm.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.api.request.ConfigTauxRequest;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.service.expense.ExpenseSettingsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/configs")
public class ExpenseConfigController {
    
    @Autowired
    private ExpenseSettingsService expenseSettingsService;

    @PostMapping("/update/taux")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody ConfigTauxRequest configTauxRequest) {
        System.out.println(configTauxRequest);
        try {
            expenseSettingsService.updateExpenseSettings(configTauxRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("taux not updated",e));
        }
        return ResponseEntity.ok(ApiResponseBuilder.success("taux updated", true));
    }
    
}
