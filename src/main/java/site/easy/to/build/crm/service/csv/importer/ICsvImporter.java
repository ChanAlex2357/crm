package site.easy.to.build.crm.service.csv.importer;

import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.exception.ImportException;

public interface ICsvImporter<T,G> {
    public ImportFileCsvResult<T> importData(MultipartFile file);
    public void transfer(G mapping , T entity,ImportException exception);
    public void validation(T entity , ImportException exception);
}   
