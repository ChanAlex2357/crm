package site.easy.to.build.crm.service.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.csv.CrmFiles;
import site.easy.to.build.crm.entity.csv.FilesMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.exception.ManagerImportException;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.csv.importer.BudgetImportService;
import site.easy.to.build.crm.service.csv.importer.CustomerImportService;
import site.easy.to.build.crm.service.csv.importer.ExpenseImportService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.expense.ExpenseService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

@Service
public class CrmImportService {

    @Autowired
    private CustomerImportService customerImportService;

    @Autowired
    private BudgetImportService budgetImportService;

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ExpenseImportService expenseImportService;

    @Autowired
    private ExpenseService expenseService;

    @Transactional
    public  ImportMapFilesCsvResult importData(CrmFiles formData,User manager){
        ImportMapFilesCsvResult importResults =  new ImportMapFilesCsvResult();
        // Importation des customers
        ImportFileCsvResult<Customer> customerResult = customerImportService.importData(formData.getCustomerFile());
        importResults.addImportFileCsvResult(customerResult);

        try {
            customerService.saveAll(customerResult.getData(),manager,customerResult.getExceptions());
        } catch (Exception e) {

        }
            // Persistance de de chaque ligne de donnee
        // Importation de budgets
        ImportFileCsvResult<Budget> budgetResult = budgetImportService.importData(formData.getBudgetFile());
        importResults.addImportFileCsvResult(budgetResult);

        try {
            budgetService.saveAll(budgetResult.getData(),manager,budgetResult.getExceptions());
        } catch (Exception e) {
            // TODO: handle exception
        }

        ImportFileCsvResult<Expense> expenResult = expenseImportService.importData(formData.getExpenseFile());
        importResults.addImportFileCsvResult(expenResult);

        try {
            expenseService.saveAll(expenResult.getData(),manager,expenResult.getExceptions());   
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (importResults.hasErrors()) {
            throw new RuntimeException(importResults.getErrorHtml());
        }
    
        return importResults;
    }
}
