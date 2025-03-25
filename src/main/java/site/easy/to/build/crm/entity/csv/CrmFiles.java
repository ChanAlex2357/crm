package site.easy.to.build.crm.entity.csv;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
@Data
public class CrmFiles implements FilesMapping{
    MultipartFile customerFile;
    MultipartFile expenseFile;
    MultipartFile budgetFile;
}
