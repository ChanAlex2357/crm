package site.easy.to.build.crm.entity.csv.mapping;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
@Getter
public class BudgetMapping extends CsvMapping {
    /* 
        +----------------+--------------+------+-----+---------+----------------+
        | Field          | Type         | Null | Key | Default | Extra          |
        +----------------+--------------+------+-----+---------+----------------+
        | customer_email | varchar(255) | YES  |     | NULL    |                |
        | Budget         | decimal(15,2)| YES  |     | NULL    |                |
        +----------------+--------------+------+-----+---------+----------------+
    */

    @CsvBindByName(column = "customer_email", required = true)
    private String customerEmail;

    @CsvBindByName(column = "Budget", required = true)
    private BigDecimal amount;
}
