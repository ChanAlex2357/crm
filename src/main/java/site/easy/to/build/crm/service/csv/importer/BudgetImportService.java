package site.easy.to.build.crm.service.csv.importer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.BudgetMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.csv.CSVService;
import site.easy.to.build.crm.service.csv.validation.ConstrainValidator;
import site.easy.to.build.crm.service.customer.CustomerService;

@Service
@Slf4j
public class BudgetImportService implements ICsvImporter<Budget, BudgetMapping> {

    @Autowired
    private CSVService<Budget, BudgetMapping> csvParser;

    @Autowired
    private ConstrainValidator<Budget> constrainValidator;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public ImportFileCsvResult<Budget> importData(MultipartFile file) {
        ImportFileCsvResult<Budget> result = new ImportFileCsvResult<>(file);
        List<BudgetMapping> csvRecords = csvParser.parseCSV(file, BudgetMapping.class, ',');

        int line = 1;
        for (BudgetMapping budgetMapping : csvRecords) {
            ImportException exception = result.getImportException(line++);
            Budget budget = new Budget();
            transfer(budgetMapping, budget, exception);
            validation(budget, exception);
            result.addData(budget);
        }
        return result;
    }

    @Override
    @Transactional
    public void transfer(BudgetMapping mapping, Budget budget,ImportException exception) {
        Customer customer = customerService.findByEmail(mapping.getCustomerEmail());
        // controlle de customer
        if (customer == null) {
            exception.addError("Customer with email : "+mapping.getCustomerEmail()+" doesn't exist");
        }
        budget.setCustomer(customer);
        budget.setAmount(mapping.getAmount());
        budget.setCreatedAt(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void validation(Budget entity, ImportException exception) {
        constrainValidator.validateConstraint(entity, exception);
    }
}
