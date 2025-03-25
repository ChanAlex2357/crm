package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.csv.ImportCsvResult;
import site.easy.to.build.crm.entity.csv.ImportData;
import site.easy.to.build.crm.entity.csv.ImportFormData;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.service.csv.LeadImportService;
import site.easy.to.build.crm.service.csv.general.AdminImportService;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/employee/import")
public class ImportController {
    
    @Autowired
    private LeadImportService leadImportService;

    @Autowired
    private LeadServiceImpl leadService ;

    @Autowired
    private AdminImportService importService;

    @GetMapping
    public String importForm(Model model) {
        model.addAttribute("importData",new ImportFormData());
        return "import/form";

    }

    @PostMapping
    public String postMethodName(@ModelAttribute ImportFormData formData) {
        System.out.println(formData.getCustomerFile().getOriginalFilename());
        System.out.println(formData.getCustomerDataFile().getOriginalFilename());
        
        return "redirect:/";
    }
    

    @PostMapping("/import/lead")
    public String handleImport(@ModelAttribute ImportData importForm, Model model) {
        
        ImportCsvResult importBody = null;
        try {
            MultipartFile file = importForm.getFile();
            importBody =  importService.importData(file, leadImportService, ',');
            if (importBody == null) {
                throw new Exception("Error while importing data");
            }
            // Verification des erreurs
            if (importBody.getImportException().hasErrors()) {
                throw importBody.getImportException();
            }
            // Persistance de de chaque ligne de donnee
            for (Object leadObject : importBody.getData()) {
                leadService.save(
                    (Lead) leadObject
                );
            }
        } catch (AdminImportException e) {
            model.addAttribute("errors", e);
            model.addAttribute("importData",new ImportData("lead"));
            return "import/import-lead";
        } catch ( Exception exc){
            model.addAttribute("importData",new ImportData("lead"));
            return "import/import-lead";
        }
        return "redirect:/employee/lead/assigned-leads";
    }
}
