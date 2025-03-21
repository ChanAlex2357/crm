package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.importdata.ImportData;

@Controller
public class ImportController {
    
    @GetMapping("/import")
    public String importForm(Model model){
        model.addAttribute("importData",new ImportData());
        return "import/import-lead";
    }

    @PostMapping("/import")
    public String handleImport(@ModelAttribute ImportData importForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "import/import-lead";
        }
        // Process the uploaded CSV file
        MultipartFile file = importForm.getFile();
        System.out.println(file);
        return "redirect:/";
    }
}
