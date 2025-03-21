package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.imp.ImportData;
import site.easy.to.build.crm.entity.imp.LeadMapping;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.service.imp.AdminImportService;
import site.easy.to.build.crm.service.lead.LeadImportService;

@Controller
public class ImportController {
    
    @Autowired
    LeadImportService leadImportService = new LeadImportService();

    @Autowired
    AdminImportService importService = new AdminImportService();

    @GetMapping("/import/{base}")
    public String importForm(Model model,@PathVariable("base") String base) {
        if(base == null) {
            return "redirect:/error-404";
        }
        model.addAttribute("importData",new ImportData(base));
        return "import/import-lead";

    }

    @PostMapping("/import/lead")
    public String handleImport(@ModelAttribute ImportData importForm, Model model) {
        try {
            MultipartFile file = importForm.getFile();
            importService.importData(file, leadImportService, ',');
        } catch (AdminImportException e) {
            model.addAttribute("errors", e);
            return "import/lead";
        }
        return "redirect:/employee/lead/assigned-leads";
    }
}
