package site.easy.to.build.crm.entity.imp;

import java.sql.Date;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;

@Getter
public class LeadMapping extends CsvMapping{
    /* 
        +------------------------+--------------+------+-----+---------+----------------+
        | Field                  | Type         | Null | Key | Default | Extra          |
        +------------------------+--------------+------+-----+---------+----------------+
        | lead_id                | int unsigned | NO   | PRI | NULL    | auto_increment |
        | customer_id            | int unsigned | NO   | MUL | NULL    |                |
        | user_id                | int          | YES  | MUL | NULL    |                |
        | name                   | varchar(255) | YES  |     | NULL    |                |
        | phone                  | varchar(20)  | YES  |     | NULL    |                |
        | employee_id            | int          | YES  | MUL | NULL    |                |
        | status                 | varchar(50)  | YES  |     | NULL    |                |
        | meeting_id             | varchar(255) | YES  | UNI | NULL    |                |
        | google_drive           | tinyint(1)   | YES  |     | NULL    |                |
        | google_drive_folder_id | varchar(255) | YES  |     | NULL    |                |
        | created_at             | datetime     | YES  |     | NULL    |                |
        +------------------------+--------------+------+-----+---------+----------------+
    */ 

    @CsvBindByName(column = "lead_id")
    private int leadId;
    
    @CsvBindByName(column = "customer_id", required = true)
    private int customerId;
    
    @CsvBindByName(column = "user_id",required =  true)
    private int userId;
    
    @CsvBindByName(column = "name",required = true)
    private String name;
    
    @CsvBindByName(column = "phone" ,required = true)
    private String phone;

    @CsvBindByName(column = "employee_id" , required = true)
    private int employeeId;

    @CsvBindByName(column = "status" , required = true)
    private String status;

    @CsvBindByName(column = "meeting_id")
    private String meetingId;

    @CsvBindByName(column = "google_drive")
    private int googleDrive;

    @CsvBindByName(column = "google_drive_folder_id")
    private String googleDriveFolderId;

    @CsvBindByName(column = "created_at")
    private Date  createdAt;
}
