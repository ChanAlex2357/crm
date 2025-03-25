package site.easy.to.build.crm.service.csv;
import java.util.List;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.csv.FilesMapping;
import site.easy.to.build.crm.entity.csv.form.CrmFiles;
import site.easy.to.build.crm.entity.csv.form.IFilesFormData;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;

@Service
public class CSVImporter {
    public ImportMapFilesCsvResult importData(IFilesFormData formData,CrmFiles filesMapping) {
        ImportMapFilesCsvResult importCsvResult = new ImportMapFilesCsvResult(); // prepare result
        List<ImportCsvManager<?,? extends CsvMapping>> managers = formData.getManagers();
        // Customer Import
        ImportFileCsvResult importFileCsvResult = managers.get(0).importData(filesMapping.getCustomerFile(),';'); 
        importCsvResult.addImportFileCsvResult(importFileCsvResult);
        
        return importCsvResult;
    }
}
