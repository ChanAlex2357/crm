package site.easy.to.build.crm.entity.csv.results;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class ImportMapFilesCsvResult {
   List<ImportFileCsvResult<?>> fileCsvResults;
   public ImportMapFilesCsvResult(){
      setFileCsvResults(new ArrayList<>());
   }
   public void addImportFileCsvResult(ImportFileCsvResult<?> fileResult){
      getFileCsvResults().add(fileResult);
   }

   public boolean hasErrors(){
      for (ImportFileCsvResult<?> importFileCsvResult : fileCsvResults) {
         if (importFileCsvResult.hasErrors()) {
            return true;
         }
      }
      return false;
   }
}
