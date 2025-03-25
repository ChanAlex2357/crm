package site.easy.to.build.crm.service.csv;

import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.exception.ImportException;

public interface IImportService<T,G extends CsvMapping> {
    T parseFromMappinToInstance(G mapping , ImportException lineException);
    T saveInstance(T data,ImportException lineException);
    Class<G> getMapping();
}
