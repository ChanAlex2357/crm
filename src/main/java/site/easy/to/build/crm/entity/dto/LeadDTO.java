package site.easy.to.build.crm.entity.dto;

import java.time.LocalDateTime;

public interface LeadDTO {
    Integer getLeadId();
    String getLeadName();
    String getLeadStatus();
    String getLeadPhone();
    String getMeetingId();
    Boolean getGoogleDrive();
    String getGoogleDriveFolderId();
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
