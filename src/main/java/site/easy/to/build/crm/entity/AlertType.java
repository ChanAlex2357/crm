package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alert_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "val", length = 100)
    private String val;

    @Column(name = "desce", length = 100)
    private String desce;
}
