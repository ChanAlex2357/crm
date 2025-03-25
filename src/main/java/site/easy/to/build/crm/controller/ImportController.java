package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import site.easy.to.build.crm.entity.csv.CrmFiles;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.entity.csv.results.ImportMapFilesCsvResult;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.csv.importer.BudgetImportService;
import site.easy.to.build.crm.service.csv.importer.CustomerImportService;
import site.easy.to.build.crm.service.customer.CustomerService;


@Controller
@RequestMapping("/employee/import")
@Transactional  
public class ImportController {

    @Autowired
    private CustomerImportService customerImportService;

    @Autowired
    private BudgetImportService budgetImportService;

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String importForm(Model model,RedirectAttributes redirectAttributes) {
        model.addAttribute("importData",new CrmFiles());
        return "import/form";

    }

    @PostMapping
    @Transactional
    public String postMethodName(@ModelAttribute CrmFiles formData,RedirectAttributes redirectAttributes) {
        ImportMapFilesCsvResult importResults =  new ImportMapFilesCsvResult();
        try {
            // Importation des customers
            ImportFileCsvResult<Customer> customerResult = customerImportService.importData(formData.getCustomerFile());
            importResults.addImportFileCsvResult(customerResult);
    
            // Persistance de de chaque ligne de donnee
            customerService.saveAll(customerResult.getData());
            // Importation de budgets
            ImportFileCsvResult<Budget> budgetResult = budgetImportService.importData(formData.getBudgetFile());
            importResults.addImportFileCsvResult(budgetResult);
    
            if (importResults.hasErrors()) {
                redirectAttributes.addFlashAttribute("importErrors",importResults.getErrorHtml());
                return "redirect:/employee/import";
            }
            // Persistance de budget
            budgetService.saveAll(budgetResult.getData());
            
        } catch (Exception e) {
            e.printStackTrace();
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
