package site.easy.to.build.crm.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.api.request.ConfigTauxRequest;
import site.easy.to.build.crm.entity.api.response.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/config/taux")
public class ExpenseConfigController {
    

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody ConfigTauxRequest configTauxRequest) {
        
        System.out.println(configTauxRequest);
        return ResponseEntity.ok(ApiResponseBuilder.success("taux updated", configTauxRequest));
    }
    
}
