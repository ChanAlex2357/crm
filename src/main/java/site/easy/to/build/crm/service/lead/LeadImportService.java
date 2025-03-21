package site.easy.to.build.crm.service.lead;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.imp.LeadMapping;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.service.imp.ImportService;

@Slf4j
public class LeadImportService extends ImportService<LeadMapping>{

    public LeadImportService() {
        super(LeadMapping.class);
    }
    @Override
    protected void importData(List<LeadMapping> data , AdminImportException importException) {
        log.info("-- IMPORTATION DES LEADS");
    }
    
}
