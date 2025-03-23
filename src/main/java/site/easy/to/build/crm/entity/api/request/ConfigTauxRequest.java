package site.easy.to.build.crm.entity.api.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfigTauxRequest {
    
    @JsonProperty("taux")
    private double taux;

    @JsonProperty("date_taux")
    private Date  dateTaux; 
}
