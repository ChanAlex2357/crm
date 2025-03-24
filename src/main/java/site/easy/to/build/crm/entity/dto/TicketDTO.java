package site.easy.to.build.crm.entity.dto;

import java.time.LocalDateTime;

public interface TicketDTO {
    Integer getTicketId();
    String getSubject();
    String getDescription();
    String getTicketStatus();
    String getPriority();
    LocalDateTime getCreatedAt();
    
    // Customer information
    Integer getCustomerId();
    String getCustomerName();
    String getCustomerEmail();
    
    // Manager information
    Integer getManagerId();
    String getManagerUsername();
    String getManagerEmail();
    
    // Employee information
    Integer getEmployeeId();
    String getEmployeeUsername();
    String getEmployeeEmail();
}
