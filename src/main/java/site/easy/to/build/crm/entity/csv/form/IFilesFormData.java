package site.easy.to.build.crm.entity.csv.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.csv.providers.ImportDataProvider;

public interface IFilesFormData {
    public List<ImportDataProvider> getImportsData();
}
