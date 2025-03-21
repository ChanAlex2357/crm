package site.easy.to.build.crm.service.csv.general;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import site.easy.to.build.crm.entity.csv.CsvMapping;
import site.easy.to.build.crm.entity.csv.ImportBody;


@Service
@Getter
public abstract class ImportService {
    Class<? extends CsvMapping> mapping;
    public ImportService(Class<? extends CsvMapping> mapping){
        this.mapping = mapping;
    }
    abstract protected void importData(List<? extends CsvMapping> data,ImportBody importBody);
}
