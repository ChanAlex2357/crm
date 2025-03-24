package site.easy.to.build.crm.entity.api.request;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfigTauxRequest {
    
    @JsonProperty("taux")
    private double taux;

    @JsonProperty("date_taux")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime  dateTaux; 
}
