package site.easy.to.build.crm.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import site.easy.to.build.crm.entity.imp.CsvMapping;
import site.easy.to.build.crm.exception.AdminImportException;


@Service
@Getter
public abstract class ImportService<T extends CsvMapping> {
    Class<? extends T> mapping;
    public ImportService(Class<? extends T> mapping){
        this.mapping = mapping;
    }
    abstract protected void importData(List<T> data,AdminImportException importException);
}
