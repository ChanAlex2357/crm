package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.easy.to.build.crm.service.DatabaseService;

@Controller
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/reset")
    public String resetDatabase(Model model) {
        try {
            databaseService.deleteAllTables();
            model.addAttribute("message", "Database reset successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error resetting database: " + e.getMessage());
        }
        return "redirect:/"; // Redirect to the home page or any other page
    }
}
