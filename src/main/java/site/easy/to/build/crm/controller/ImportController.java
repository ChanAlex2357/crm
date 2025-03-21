package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.imp.ImportData;
import site.easy.to.build.crm.service.imp.AdminImportService;

@Controller
public class ImportController {
    
    @GetMapping("/import/{base}")
    public String importForm(Model model,@PathVariable("base") String base) {
        if(base == null) {
            return "redirect:/error-404";
        }
        model.addAttribute("importData",new ImportData(base));
        return "import/import-lead";

    }

    @PostMapping("/import")
    public String handleImport(@ModelAttribute ImportData importForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "import/import-lead";
        }

        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        AdminImportService<?> importService = new AdminImportService<>();
        // Process the uploaded CSV file
        MultipartFile file = importForm.getFile();
        System.out.println(file);
        return "redirect:/";
    }
}
