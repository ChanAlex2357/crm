package site.easy.to.build.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_alert")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer alertId;

    @Column(name = "budget_amount", precision = 15, scale = 2)
    private BigDecimal budgetAmount;

    @Column(name = "taux", precision = 15, scale = 2)
    private BigDecimal taux;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "expense_amount", precision = 15, scale = 2)
    private BigDecimal expenseAmount;

    @Column(name = "date_alert")
    private LocalDate dateAlert;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private CustomerExpense expense;

    @ManyToOne
    @JoinColumn(name = "alert_type_id", nullable = false)
    private AlertType alertType;
}

