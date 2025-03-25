package site.easy.to.build.crm.entity.csv;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImportFormData {
    MultipartFile customerFile;
    MultipartFile customerDataFile;
}
