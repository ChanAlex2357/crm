package site.easy.to.build.crm.entity.importdata;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
@Data
public class ImportData {
    private MultipartFile file;
}
