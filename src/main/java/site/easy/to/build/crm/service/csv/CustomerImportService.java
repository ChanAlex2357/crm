package site.easy.to.build.crm.service.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;

@Service
public class CustomerImportService implements IImportService<Customer,CustomerMapping> {
    @Autowired
    private CustomerServiceImpl customerService;


    @Override
    public Customer parseFromMappinToInstance(CustomerMapping mapping, ImportException lineException) {
        Customer customer = new Customer();
        customer.setName(mapping.getName());
        customer.setEmail(mapping.getEmail());
        return customer;
    }
    @Override
    public Customer saveInstance(Customer data, ImportException lineException) {
        return null;
    }
    @Override
    public Class<CustomerMapping> getMapping() {
        return CustomerMapping.class;
    }
    
}
