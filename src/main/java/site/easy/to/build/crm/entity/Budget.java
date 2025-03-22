package site.easy.to.build.crm.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "customer_budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int budgetId;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Amount is required")
    @Digits(integer = 15, fraction = 2, message = "Amount must be a valid number with up to 2 decimal places")
    @DecimalMin(value = "0.00", inclusive = true, message = "Amount must be greater than or equal to 0.00")
    @DecimalMax(value = "9999999.99", inclusive = true, message = "Amount must be less than or equal to 9999999.99")
    private BigDecimal amount;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "start_date", nullable = false)
    @NotBlank(message = "Start Date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
    private String startDate;

    @Column(name = "end_date", nullable = false)
    @NotBlank(message = "End Date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "currency")
    @NotNull(message = "Currency is required")
    private Currency currency;
}
