package site.easy.to.build.crm.entity.csv;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
@Data
public class CrmFiles implements FilesMapping{
    MultipartFile customerFile;
    MultipartFile expenseFile;
    MultipartFile budgetFile;


    public int getSize() {
        int size = 0;
        if(customerFile != null) size += customerFile.getSize();
        if(expenseFile != null) size += expenseFile.getSize();
        if(budgetFile != null) size += budgetFile.getSize();
        return size;
    }
}
