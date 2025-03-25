package site.easy.to.build.crm.entity.csv.mapping;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class CustomerMapping extends CsvMapping {
    /* 
        +----------------+--------------+------+-----+---------+----------------+
        | Field          | Type         | Null | Key | Default | Extra          |
        +----------------+--------------+------+-----+---------+----------------+
        | customer_id    | int unsigned | NO   | PRI | NULL    | auto_increment |
        | name           | varchar(255) | YES  |     | NULL    |                |
        | phone          | varchar(20)  | YES  |     | NULL    |                |
        | address        | varchar(255) | YES  |     | NULL    |                |
        | city           | varchar(255) | YES  |     | NULL    |                |
        | state          | varchar(255) | YES  |     | NULL    |                |
        | country        | varchar(255) | YES  |     | NULL    |                |
        | user_id        | int          | YES  | MUL | NULL    |                |
        | description    | text         | YES  |     | NULL    |                |
        | position       | varchar(255) | YES  |     | NULL    |                |
        | twitter        | varchar(255) | YES  |     | NULL    |                |
        | facebook       | varchar(255) | YES  |     | NULL    |                |
        | youtube        | varchar(255) | YES  |     | NULL    |                |
        | created_at     | datetime     | YES  |     | NULL    |                |
        | email          | varchar(255) | YES  |     | NULL    |                |
        | profile_id     | int          | YES  | MUL | NULL    |                |
        +----------------+--------------+------+-----+---------+----------------+
    */

    @CsvBindByName(column = "name", required = true)
    private String name;

    @CsvBindByName(column = "email", required = true)
    private String email;
}