package site.easy.to.build.crm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.service.ticket.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketApiController {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<ApiResponse> getTicketSummary() {
        try {
            var tickets = ticketService.findAll();
            ApiResponse apiResponse = ApiResponseBuilder.success("Total des tickets", tickets);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting tickets", e));
        }
    }
}

