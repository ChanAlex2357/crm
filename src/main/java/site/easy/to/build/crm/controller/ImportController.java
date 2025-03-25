package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.easy.to.build.crm.entity.csv.CrmFiles;


@Controller
@RequestMapping("/employee/import")
public class ImportController {
    @GetMapping
    public String importForm(Model model) {
        model.addAttribute("importData",new CrmFiles());
        return "import/form";

    }

    // @PostMapping
    // public String postMethodName(@ModelAttribute CrmFiles formData,RedirectAttributes redirectAttributes) {
    //     ImportMapFilesCsvResult importResults =  csvImporter.importData(dataManager,formData);
    //     if (importResults.hasErrors()) {
    //         redirectAttributes.addAttribute("importErrors",importResults);
    //         return "";
    //     }
    //     redirectAttributes.addAttribute("importMessage","Donnee importer avec success");
    //     return "redirect:/employee/import/form";
    // }
    

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
