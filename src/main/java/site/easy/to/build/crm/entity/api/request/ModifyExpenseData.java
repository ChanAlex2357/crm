package site.easy.to.build.crm.entity.api.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModifyExpenseData {
    @JsonProperty("LeadId")
    int leadId;
    @JsonProperty("Expense")
    BigDecimal expense;
}
