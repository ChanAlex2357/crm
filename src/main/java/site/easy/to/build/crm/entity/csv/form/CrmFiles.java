package site.easy.to.build.crm.entity.csv.form;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import site.easy.to.build.crm.entity.csv.FilesMapping;
@Data
public class CrmFiles implements FilesMapping{
    MultipartFile customerFile;
    MultipartFile expenseFile;
    MultipartFile budgetFile;
}
