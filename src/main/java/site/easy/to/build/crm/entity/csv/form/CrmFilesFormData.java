package site.easy.to.build.crm.entity.csv.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.service.csv.CustomerImportService;
import site.easy.to.build.crm.service.csv.ImportCsvManager;

public class CrmFilesFormData implements IFilesFormData{
    MultipartFile customerFile;
    MultipartFile expenseFile;
    MultipartFile budgetFile;

    @Override
    public List<ImportCsvManager<?,? extends CsvMapping>> getManagers() {
        ArrayList<ImportCsvManager<?,? extends CsvMapping>> files = new ArrayList<ImportCsvManager<?,? extends CsvMapping>>();
        
        files.add(
            new ImportCsvManager<Customer,CustomerMapping>(
                customerFile,
                new CustomerImportService()
            )
        );
        // TODO (Alex) :  cree l'import data provider pour expenses
        // TODO (Alex) :  cree l'import data provider pour budgets
        return files;
    }

    public MultipartFile getCustomerFile() {
        return customerFile;
    }

    public void setCustomerFile(MultipartFile customerFile) {
        this.customerFile = customerFile;
    }

    public MultipartFile getExpenseFile() {
        return expenseFile;
    }

    public void setExpenseFile(MultipartFile expenseFile) {
        this.expenseFile = expenseFile;
    }

    public MultipartFile getBudgetFile() {
        return budgetFile;
    }

    public void setBudgetFile(MultipartFile budgetFile) {
        this.budgetFile = budgetFile;
    }
}
