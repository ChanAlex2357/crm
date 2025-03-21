package site.easy.to.build.crm.service.lead;

import java.util.List;
import site.easy.to.build.crm.entity.imp.LeadMapping;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.service.imp.ImportService;

public class LeadImportService extends ImportService<LeadMapping>{

    public LeadImportService() {
        super(LeadMapping.class);
    }
    @Override
    protected void importData(List<LeadMapping> data , AdminImportException importException) {
        // TODO chan alex : custom importation de donnee pour  Lead
    }
    
}
