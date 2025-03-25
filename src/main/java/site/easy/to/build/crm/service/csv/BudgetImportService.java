package site.easy.to.build.crm.service.csv;

import java.util.List;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportCsvResult;

@Service
public class BudgetImportService extends ImportCsvService{

    public BudgetImportService(Class<? extends CsvMapping> mapping) {
        super(mapping);
    }
    
    @Override
    protected void importData(List<? extends CsvMapping> data, ImportCsvResult importBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }
    
}
