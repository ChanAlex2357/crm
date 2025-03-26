package site.easy.to.build.crm.entity.csv.mapping;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import site.easy.to.build.crm.entity.Expense;

@Getter
public class ExpenseMapping extends CsvMapping {
    /* 
        +----------------+--------------+------+-----+---------+----------------+
        | Field          | Type         | Null | Key | Default | Extra          |
        +----------------+--------------+------+-----+---------+----------------+
        | customer_email | varchar(255) | YES  |     | NULL    |                |
        | subject_or_name| varchar(255) | YES  |     | NULL    |                |
        | type           | varchar(50)  | YES  |     | NULL    |                |
        | status         | varchar(50)  | YES  |     | NULL    |                |
        | expense        | decimal(15,2)| YES  |     | NULL    |                |
        +----------------+--------------+------+-----+---------+----------------+
    */

    @CsvBindByName(column = "customer_email", required = true)
    private String customerEmail;

    @CsvBindByName(column = "subject_or_name", required = true)
    private String subjectOrName;

    @CsvBindByName(column = "type", required = true)
    private String type;

    @CsvBindByName(column = "status", required = true)
    private String status;

    @CsvBindByName(column = "expense", required = true)
    private String expense;
}
