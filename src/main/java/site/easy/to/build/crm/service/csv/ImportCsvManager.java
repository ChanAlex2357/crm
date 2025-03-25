package site.easy.to.build.crm.service.csv;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;

@Data
@Service
public class ImportCsvManager<T,G extends CsvMapping> {
    private CSVService<T,G> csvService;
    private IImportService<T,G> infService;
    private ImportCsvService<T,G> importService;
    public ImportCsvManager(CSVService<T,G> csvService,IImportService<T,G> infService,ImportCsvService<T,G> importService) {
        this.csvService = csvService;
        this.infService = infService;
        this.importService = importService;
    }

    public ImportFileCsvResult  importData(MultipartFile file,char separator) {
        ImportFileCsvResult fileImportResult = new ImportFileCsvResult(file);
        try {
            List<G> data = csvService.fromCsvToCsvMappping(file,this,separator);
            getImportService().importData(data,fileImportResult,getInfService());
        } catch (Exception e) {
            System.out.println("--------------------------------------------------------------");
            e.printStackTrace();
            System.out.println("--------------------------------------------------------------");
        }
        return fileImportResult;
    }
}
