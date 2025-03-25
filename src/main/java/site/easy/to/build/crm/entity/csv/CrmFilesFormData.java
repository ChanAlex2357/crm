package site.easy.to.build.crm.entity.csv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.mapping.CustomerMapping;
import site.easy.to.build.crm.service.csv.CustomerImportService;

public class CrmFilesFormData implements IFilesFormData{
    MultipartFile customerFile;
    MultipartFile expenseFile;
    MultipartFile budgetFile;

    @Override
    public List<ImportDataProvider> getImportsData() {
        ArrayList<ImportDataProvider> files = new ArrayList<ImportDataProvider>();
        
        files.add(new ImportDataProvider(customerFile, new CustomerImportService(CustomerMapping.class)));
        // TODO (Alex) :  cree l'import data provider pour expenses
        // TODO (Alex) :  cree l'import data provider pour budgets
        return files;
    }
}
