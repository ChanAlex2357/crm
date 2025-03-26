package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.transaction.Transactional;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.csv.CrmFiles;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.csv.CrmImportService;
import site.easy.to.build.crm.service.csv.importer.BudgetImportService;
import site.easy.to.build.crm.service.csv.importer.CustomerImportService;
import site.easy.to.build.crm.service.csv.importer.ExpenseImportService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.expense.ExpenseService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;


@Controller
@RequestMapping("/employee/import")
public class ImportController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationUtils authenticationUtils;

    @Autowired
    private CrmImportService importService;

    
    @GetMapping
    public String importForm(Model model,RedirectAttributes redirectAttributes) {
        model.addAttribute("importData",new CrmFiles());
        return "import/form";

    }

    @PostMapping
    public String postMethodName(@ModelAttribute CrmFiles formData,RedirectAttributes redirectAttributes,Authentication authentication) {
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User manager = userService.findById(userId);
        ImportMapFilesCsvResult importResults =  new ImportMapFilesCsvResult();
        try {
            if (formData.getSize() < 3) {
                throw new RuntimeException("Aucune des 3 fichiers ne doivent etre vide");
            }
            importResults = importService.importData(formData,manager);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("importErrors",e.getMessage());
            return "redirect:/employee/import";
        }
        redirectAttributes.addFlashAttribute("importMessage","Donnee importer avec success");
        return "redirect:/employee/import";
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
