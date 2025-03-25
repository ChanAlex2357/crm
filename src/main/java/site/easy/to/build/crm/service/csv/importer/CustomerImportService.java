package site.easy.to.build.crm.service.csv.importer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.easy.to.build.crm.controller.CustomerEmailSettingController;
import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.CustomerLoginInfo;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.csv.CSVService;
import site.easy.to.build.crm.service.csv.validation.ConstrainValidator;
import site.easy.to.build.crm.service.customer.CustomerLoginInfoService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.util.EmailTokenUtils;

@Service
@Slf4j
public class CustomerImportService  implements ICsvImporter<Customer,CustomerMapping> {

    private final CustomerEmailSettingController customerEmailSettingController;

    // -- Services utilitaires pour customer --
    @Autowired
    private CustomerLoginInfoService customerProfileService;

    @Autowired 
    private CustomerService customerService;

    @Autowired
    private BudgetService budgetService;

  
    
    //  -- SERVICES Pour l'import --
    @Autowired
    private CSVService<Customer,CustomerMapping> csvParser;
    
    @Autowired
    private ConstrainValidator<Customer> constrainValidator;

    CustomerImportService(CustomerEmailSettingController customerEmailSettingController) {
        this.customerEmailSettingController = customerEmailSettingController;
    }

    // - Fonctions importation de donnee
    @Transactional
    public ImportFileCsvResult<Customer> importData(MultipartFile file){
        // Prepare the result
        ImportFileCsvResult<Customer> result = new ImportFileCsvResult<Customer>(file);
        List<CustomerMapping> csvRecords = csvParser.parseCSV(file,CustomerMapping.class,';');

        int line = 1;
        for (CustomerMapping customerMapping : csvRecords) {
            ImportException exception = result.getImportException(line++);
            Customer customer = new Customer(); // Cree une instance
            transfer(customerMapping, customer,exception); // Transferer les donnee csv dans l'instance
            validation(customer, exception);
            if (!result.hasErrors()) {
                Customer createdCustomer = save(customer, exception);
                result.addData(createdCustomer);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void transfer(CustomerMapping mapping, Customer entity,ImportException exception) {

        entity.setName(mapping.getName());
        entity.setEmail(mapping.getEmail());
        entity.setCountry("Madagascar");
        if (customerService.findByEmail(mapping.getEmail()) != null) {
            // exception.addError("Customer with email : "+mapping.getEmail()+" already exist");
        }
        else if (customerService.findByName(mapping.getName()) != null){
            // exception.addError("Customer with name : "+mapping.getName()+" already exist");
        }
    }

    @Transactional
    @Override
    public Customer save(Customer customer, ImportException exception) {
        // Create an customer login info
        CustomerLoginInfo customerLoginInfo = new CustomerLoginInfo();
        customerLoginInfo.setEmail(customer.getEmail());
        String token = EmailTokenUtils.generateToken();
        customerLoginInfo.setToken(token);
        customerLoginInfo.setPasswordSet(false);
        
        CustomerLoginInfo customerLoginInfo1 = customerProfileService.save(customerLoginInfo);
        
        // Assossiation du customer et son login
        customer.setCustomerLoginInfo(customerLoginInfo1);
        customer.setCreatedAt(LocalDateTime.now());
        Customer createdCustomer = customerService.save(customer);
        
        //  generer un budget debutant a 0 pour le customer
        budgetService.createInitialBudget(createdCustomer);
        
        return createdCustomer;
    }

    @Override
    public void validation(Customer entity, ImportException exception) {
        constrainValidator.validateConstraint(entity, exception);
    }
}
