package site.easy.to.build.crm.service.csv;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;

@Data
public class ImportCsvManager<T,G extends CsvMapping> {
    private CSVService<T,G> csvService;
    private MultipartFile file;
    private IImportService<T,G> infService;
    private ImportCsvService<T,G> importService;
    private char separator;
    public ImportCsvManager(MultipartFile file,IImportService<T,G> inf){
        csvService = new CSVService<T,G>();
        setFile(file);
        setInfService(inf);
        setImportService(new ImportCsvService<T,G>());
        setSeparator(separator);
    }

    public ImportFileCsvResult  importData(){
        ImportFileCsvResult fileImportResult = new ImportFileCsvResult(getFile());
        try {
            List<G> data = csvService.fromCsvToCsvMappping(this);
            getImportService().importData(data,fileImportResult,getInfService());
        } catch (Exception e) {
            System.out.println("--------------------------------------------------------------");
            e.printStackTrace();
            System.out.println("--------------------------------------------------------------");
        }
        return fileImportResult;
    }
}
