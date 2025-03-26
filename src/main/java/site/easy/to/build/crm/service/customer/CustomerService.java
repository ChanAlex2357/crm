package site.easy.to.build.crm.service.customer;


import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;

import java.util.List;

import jakarta.transaction.Transactional;


@Transactional
public interface CustomerService {

    public Customer findByCustomerId(int customerId);

    public List<Customer> findByUserId(int userId);

    public Customer findByEmail(String email);
    
    public Customer findByName(String name);

    public List<Customer> findAll();

    public Customer save(Customer customer);
    
    public Customer createNewCustomer(Customer customer);

    public void delete(Customer customer);

    public List<Customer> getRecentCustomers(int userId, int limit);

    long countByUserId(int userId);

    public void saveAll(List<Customer> customers);
    public void saveAll(List<Customer> customers,User manager);

}
