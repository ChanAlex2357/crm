package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImportController {
    
    @GetMapping("/import")
    public String importForm(){
        return "import/import-lead";
    }
}
