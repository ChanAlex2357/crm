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
public class CSVService<T extends CsvMapping> {
    public List<T> parseCSV(MultipartFile file,Class<T> clazz,char separator){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                .withType(clazz)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSeparator(separator)
                .build();
            
            List<T> csvRecords = csvToBean.parse();
            return csvRecords;
        } catch (IOException e) {
            log.error("Error parsing CSV file", e);
            throw new RuntimeException("Failed to process CSV file: " + e.getMessage());
        }
    }
}