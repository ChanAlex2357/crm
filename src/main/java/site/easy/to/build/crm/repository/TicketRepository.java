package site.easy.to.build.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.dto.LeadDTO;
import site.easy.to.build.crm.entity.dto.TicketDTO;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public Ticket findByTicketId(int ticketId);

    public List<Ticket> findByManagerId(int id);

    public List<Ticket> findByEmployeeId(int id);

    List<Ticket> findByCustomerCustomerId(Integer customerId);

    List<Ticket> findByManagerIdOrderByCreatedAtDesc(int managerId, Pageable pageable);

    List<Ticket> findByEmployeeIdOrderByCreatedAtDesc(int managerId, Pageable pageable);

    List<Ticket> findByCustomerCustomerIdOrderByCreatedAtDesc(int customerId, Pageable pageable);

    long countByEmployeeId(int employeeId);

    long countByManagerId(int managerId);

    long countByCustomerCustomerId(int customerId);

    void deleteAllByCustomer(Customer customer);

    @Query(value = """
            SELECT 
                t.ticket_id as ticketId,
                t.subject as subject,
                t.ticket_status as status,
                t.description as description,
                t.priority as priority,
                t.created_at as createdAt,
                -- Customer information
                t.customer_id as customerId,
                t.customer_name as customerName,
                t.customer_email as customerEmail,
                -- Manager information
                t.manager_id as managerId,
                t.manager_username as managerUsername,
                t.manager_email as managerEmail,
                -- Employee information
                t.employee_id as employeeId,
                t.employee_username as employeeUsername,
                t.employee_email as employeeEmail
            FROM ticket_cpl t
            """, nativeQuery = true)
    List<TicketDTO> findAllTicketsWithDetails();
    
    @Query(value = """
            SELECT 
                t.ticket_id as ticketId,
                t.subject as subject,
                t.description as description,
                t.status as ticketStatus,
                t.priority as priority,
                t.created_at as createdAt,
                -- Customer information
                c.customer_id as customerId,
                c.name as customerName,
                c.email as customerEmail,
                -- Manager information
                m.id as managerId,
                m.username as managerUsername,
                m.email as managerEmail,
                -- Employee information
                e.id as employeeId,
                e.username as employeeUsername,
                e.email as employeeEmail
            FROM ticket_cpl t
            WHERE customer_id = :customerId
            """, nativeQuery = true)
    List<TicketDTO> findTicketsByCustomerId(Integer customerId);

    @Query(value = """
            SELECT 
                t.ticket_id as ticketId,
                t.subject as subject,
                t.description as description,
                t.status as ticketStatus,
                t.priority as priority,
                t.created_at as createdAt,
                -- Customer information
                c.customer_id as customerId,
                c.name as customerName,
                c.email as customerEmail,
                -- Manager information
                m.id as managerId,
                m.username as managerUsername,
                m.email as managerEmail,
                -- Employee information
                e.id as employeeId,
                e.username as employeeUsername,
                e.email as employeeEmail
            FROM ticket_cpl t
            WHERE ticketId = :id
            """, nativeQuery = true)
    public LeadDTO findAllTicketsWithDetails(int id);

}
