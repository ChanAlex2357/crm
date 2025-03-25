package site.easy.to.build.crm.service.csv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.form.IFilesFormData;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.providers.ImportDataProvider;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;

@Service
public abstract class ImportCsvManager {

    @Autowired
    private CSVService csvService;
    
    public ImportFileCsvResult  importData(ImportDataProvider  importDataProvider){
        ImportFileCsvResult fileImportResult = new ImportFileCsvResult(importDataProvider.getFile());
        // try {
        //     List<CsvMapping> data = csvService.parseCSV(file, importService.getMapping(), separator);
        //     importService.importData(data,fileImportResult);
        // } catch (Exception e) {
        //     String message = e.getMessage();
        //     if (e.getCause() != null) {
        //         message += "\n [ because ] :: "+e.getCause().getMessage();
        //     }
        //     fileImportResult.addErrors(List.of(message));
        // }
        return fileImportResult;
    }

    public ImportMapFilesCsvResult importData(IFilesFormData formData){
        ImportMapFilesCsvResult importCsvResult = new ImportMapFilesCsvResult(); // prepare result
        
        List<ImportDataProvider> importsData = formData.getImportsData();
        for (ImportDataProvider importDataProvider : importsData) {
            ImportFileCsvResult importFileCsvResult = importData(importDataProvider);
            importCsvResult.addImportFileCsvResult(importFileCsvResult);
        }
        
        return null;
    }
}
