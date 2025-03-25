package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.easy.to.build.crm.entity.csv.form.CrmFilesFormData;


@Controller
@RequestMapping("/employee/import")
public class ImportController {
    
    // @Autowired
    // private LeadImportService leadImportService;

    // @Autowired
    // private AdminImportService importService;

    @GetMapping
    public String importForm(Model model) {
        model.addAttribute("importData",new CrmFilesFormData());
        return "import/form";

    }

    @PostMapping
    public String postMethodName(@ModelAttribute CrmFilesFormData formData) {
        return "redirect:/";
    }
    

    // @PostMapping("/import/lead")
    // public String handleImport(@ModelAttribute ImportDataProvider importForm, Model model) {
        
    //     ImportCsvResult importBody = null;
    //     try {
    //         MultipartFile file = importForm.getFile();
    //         importBody =  importService.importData(file, leadImportService, ',');
    //         if (importBody == null) {
    //             throw new Exception("Error while importing data");
    //         }
    //         // Verification des erreurs
    //         if (importBody.getImportException().hasErrors()) {
    //             throw importBody.getImportException();
    //         }
    //         // Persistance de de chaque ligne de donnee
    //         for (Object leadObject : importBody.getData()) {
    //             leadService.save(
    //                 (Lead) leadObject
    //             );
    //         }
    //     } catch (AdminImportException e) {
    //         model.addAttribute("errors", e);
    //         model.addAttribute("importData",new ImportDataProvider("lead"));
    //         return "import/import-lead";
    //     } catch ( Exception exc){
    //         model.addAttribute("importData",new ImportDataProvider("lead"));
    //         return "import/import-lead";
    //     }
    //     return "redirect:/employee/lead/assigned-leads";
    // }
}
