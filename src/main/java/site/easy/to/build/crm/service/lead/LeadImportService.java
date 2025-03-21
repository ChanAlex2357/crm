package site.easy.to.build.crm.service.lead;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.imp.CsvMapping;
import site.easy.to.build.crm.entity.imp.LeadMapping;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.service.imp.ImportService;
@Service
@Slf4j
public class LeadImportService extends ImportService{

    public LeadImportService() {
        super(LeadMapping.class);
    }
    
    @Override
    protected void importData(List<? extends CsvMapping> data, AdminImportException importException) {
        log.info("-- IMPORTATION DES LEADS");
    }
    
}
