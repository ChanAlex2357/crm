package site.easy.to.build.crm.entity.csv;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFilesFormData {
    public List<ImportDataProvider> getImportsData();
}
