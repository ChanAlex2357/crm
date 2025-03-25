package site.easy.to.build.crm.service.csv;
import java.util.List;
import site.easy.to.build.crm.entity.csv.form.IFilesFormData;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;

public class CSVImporter {
    public ImportMapFilesCsvResult importData(IFilesFormData formData){
        ImportMapFilesCsvResult importCsvResult = new ImportMapFilesCsvResult(); // prepare result
        List<ImportCsvManager<?,? extends CsvMapping>> managers = formData.getManagers();
        for (ImportCsvManager<?,? extends CsvMapping> manager : managers) {
            ImportFileCsvResult importFileCsvResult = manager.importData(); 
            importCsvResult.addImportFileCsvResult(importFileCsvResult);
        }
        return importCsvResult;
    }
}
