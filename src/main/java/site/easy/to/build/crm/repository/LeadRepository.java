package site.easy.to.build.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.dto.LeadDTO;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
    public Lead findByLeadId(int id);

    public List<Lead> findByCustomerCustomerId(int customerId);
    public List<Lead> findByManagerId(int userId);

    public List<Lead> findByEmployeeId(int userId);

    Lead findByMeetingId(String meetingId);

    public List<Lead> findByEmployeeIdOrderByCreatedAtDesc(int employeeId, Pageable pageable);

    public List<Lead> findByManagerIdOrderByCreatedAtDesc(int managerId, Pageable pageable);

    public List<Lead> findByCustomerCustomerIdOrderByCreatedAtDesc(int customerId, Pageable pageable);

    long countByEmployeeId(int employeeId);

    long countByManagerId(int managerId);
    long countByCustomerCustomerId(int customerId);

    void deleteAllByCustomer(Customer customer);

    @Query(value = """ 
            SELECT 
                lead_id as leadId,
                lead_name as leadName,
                lead_status as leadStatus,
                lead_phone as leadPhone,
                meeting_id as meetingId,
                google_drive as googleDrive,
                google_drive_folder_id as googleDriveFolderId,
                created_at as createdAt,
                
                customer_id as customerId,
                customer_name as customerName,
                customer_email as customerEmail,
                
                manager_id as managerId,
                manager_username as managerUsername,
                manager_email as managerEmail,
                
                employee_id as employeeId,
                employee_username as employeeUsername,
                employee_email as employeeEmail
            FROM lead_cpl
            """, nativeQuery = true)
    List<LeadDTO> findAllLeadsWithDetails();
    
    @Query(value = """ 
            SELECT 
                lead_id as leadId,
                lead_name as leadName,
                lead_status as leadStatus,
                lead_phone as leadPhone,
                meeting_id as meetingId,
                google_drive as googleDrive,
                google_drive_folder_id as googleDriveFolderId,
                created_at as createdAt,
                
                customer_id as customerId,
                customer_name as customerName,
                customer_email as customerEmail,
                
                manager_id as managerId,
                manager_username as managerUsername,
                manager_email as managerEmail,
                
                employee_id as employeeId,
                employee_username as employeeUsername,
                employee_email as employeeEmail
            FROM lead_cpl 
            WHERE customer_id = :customerId
            """, nativeQuery = true)
    List<LeadDTO> findLeadsByCustomerId(Integer customerId);
}
