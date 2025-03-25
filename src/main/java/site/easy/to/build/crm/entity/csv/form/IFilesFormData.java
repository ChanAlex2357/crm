package site.easy.to.build.crm.entity.csv.form;

import java.util.List;

import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;
import site.easy.to.build.crm.service.csv.ImportCsvManager;

public interface IFilesFormData {
    public List<ImportCsvManager<?,? extends CsvMapping>> getManagers();
}
