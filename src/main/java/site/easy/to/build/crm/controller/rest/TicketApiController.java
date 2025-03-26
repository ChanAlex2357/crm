package site.easy.to.build.crm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.api.request.ModifyExpenseData;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.service.expense.ExpenseService;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketApiController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private TicketServiceImpl ticketService;

    @GetMapping
    public ResponseEntity<ApiResponse> getTicketSummary() {
        try {
            var tickets = ticketService.getAllTicketCpl();
            ApiResponse apiResponse = ApiResponseBuilder.success("Total des tickets", tickets);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting tickets", e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTicketById(@PathVariable int id) {
        try {
            var tickets = ticketService.getTicketCpl(id);
            ApiResponse apiResponse = ApiResponseBuilder.success("Ticket", tickets);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("Error while getting tickets", e));
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<ApiResponse> delteTicket(@PathVariable int ticketId){
        log.info("Deleting ticket with id {}",ticketId);

        Ticket ticket = ticketService.findByTicketId(ticketId);
        ticketService.delete(ticket);
        return ResponseEntity.ok(ApiResponseBuilder.success("Ticket deleted",true));
    }


    @PostMapping("/{ticketId}")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody ModifyExpenseData data, @PathVariable int ticketId) {
        
        Ticket currentTicket = ticketService.findByTicketId(ticketId);
        if (currentTicket.getTicketId() != data.getLeadId()) {
            return ResponseEntity.ok().body(ApiResponseBuilder.success("Ticket not found",false));
        }
        Expense e = expenseService.getExpenseByTicket(ticketId).get(0);
        e.setAmount(data.getExpense());
        expenseService.updateExpense(e);
        return ResponseEntity.ok(ApiResponseBuilder.success("Ticket updated",true));
    }
}

