package site.easy.to.build.crm.entity.settings;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private BigDecimal taux;

    @Column(name = "date_taux")
    private Date dateTaux;
}

