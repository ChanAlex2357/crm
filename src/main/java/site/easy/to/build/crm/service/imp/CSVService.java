package site.easy.to.build.crm.service.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.imp.CsvMapping;

@Service
@Slf4j
public class CSVService {
    public List<CsvMapping> parseCSV(MultipartFile file, Class<? extends CsvMapping> clazz, char separator){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<CsvMapping> csvToBean = new CsvToBeanBuilder<CsvMapping>(reader)
                .withType(clazz)
                .withSeparator(separator)
                .build();
            
            List<CsvMapping> csvRecords = csvToBean.parse();
            return csvRecords;
        } catch (IOException e) {
            log.error("Error parsing CSV file", e);
            throw new RuntimeException("Failed to process CSV file: " + e.getMessage());
        }
    }
}