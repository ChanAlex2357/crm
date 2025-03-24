package site.easy.to.build.crm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.service.lead.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadApiController {
    
    @Autowired
    private LeadService leadService;

    @GetMapping
    public ResponseEntity<ApiResponse> getLeadSummary() {
        try {
            var leads = leadService.findAll();
            ApiResponse apiResponse = ApiResponseBuilder.success("Total des leads", leads);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting leads", e));
        }
    }
}

