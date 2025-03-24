package site.easy.to.build.crm.entity.settings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.easy.to.build.crm.entity.api.request.ConfigTauxRequest;

@Entity
@Table(name = "expense_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSettings {

    public ExpenseSettings(ConfigTauxRequest configTauxRequest) {
        setTaux(BigDecimal.valueOf(configTauxRequest.getTaux()));
        setDateTaux(configTauxRequest.getDateTaux());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private BigDecimal taux;

    @Column(name = "date_taux")
    private LocalDateTime dateTaux;
}

