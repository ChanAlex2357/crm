package site.easy.to.build.crm.service.csv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportBody;
import site.easy.to.build.crm.entity.csv.mapping.LeadMapping;
import site.easy.to.build.crm.service.csv.general.ImportService;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.user.UserServiceImpl;

@Service
@Slf4j
public class LeadImportService extends ImportService{

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private CustomerServiceImpl customerService;

    public LeadImportService() {
        super(LeadMapping.class);
    }
    @Override
    protected void importData(List<? extends CsvMapping> data, ImportBody importBody) {
        log.info("-- IMPORTATION DES LEADS");
        int line = 0;
        for (CsvMapping csvMapping : data) {
            line += 1;
            LeadMapping leadMapping = (LeadMapping) csvMapping;
            Lead lead = leadMapping.transfer();
            try {
                setCustomer(leadMapping, lead);
                setEmployee(leadMapping, lead);
                setManager(leadMapping, lead);
            } catch (Exception e) {
                log.error(line+":"+e.getMessage(),e);
                importBody.addError(e.getMessage(), line);
            }
            importBody.addData(lead);
        }
    }

    private void setCustomer(LeadMapping leadMapping,Lead lead){
        Customer customer = customerService.findByCustomerId(leadMapping.getCustomerId());
        if (customer == null) {
            throw new IllegalArgumentException("Customer id invalide , impossible de trouver le client");
        }
        lead.setCustomer(customer);
    }

    private void setEmployee(LeadMapping leadMapping , Lead lead) {
        User employee = userService.findById(leadMapping.getEmployeeId());
        if (employee == null) {
            throw new IllegalArgumentException("Employee id invalide , impossible de trouver l'employee");
        }
        lead.setEmployee(employee);
    }
    private void setManager(LeadMapping leadMapping , Lead lead) {
        User manager = userService.findById(leadMapping.getUserId());
        if (manager == null) {
            throw new IllegalArgumentException("Manager id invalide , impossible de trouver le manager");
        }
        lead.setManager(manager);
    }
    
}
