package site.easy.to.build.crm.service.csv;

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
import site.easy.to.build.crm.entity.csv.mapping.CsvMapping;

@Service
@Slf4j
public class CSVService<T,G extends CsvMapping> {
    public List<G> parseCSV(MultipartFile file, Class<G> clazz, char separator){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<G> csvToBean = new CsvToBeanBuilder<G>(reader)
                .withType(clazz)
                .withSeparator(separator)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
            List<G> csvRecords = csvToBean.parse();
            return csvRecords;
        } catch (IOException e) {
            log.error("Error parsing CSV file", e);
            throw new RuntimeException("Failed to process CSV file: " + e.getMessage());
        }
    }
}