package site.easy.to.build.crm.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.easy.to.build.crm.entity.settings.ExpenseSettings;
import site.easy.to.build.crm.service.expense.ExpenseSettingsService;
import site.easy.to.build.crm.service.user.UserServiceImpl;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

@Controller
@RequestMapping("/manager/settings")
public class ManagerSettingController {

    @Autowired
    AuthenticationUtils authenticationUtils;

    @Autowired
    ExpenseSettingsService expenseSettingsService;

    @Autowired
    UserServiceImpl userService;
    @GetMapping("/expense")
    public String showExpenseSetting(Model model,Authentication authentication) {
        if (!AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")){
            return "error/access-denied";
        }
        ExpenseSettings expenseSettings = expenseSettingsService.getLatestExpenseSettings();
        model.addAttribute("config",expenseSettings);
        return "settings/expense";
    }
    
    @PostMapping("/expense")
    public String saveExpenseSetting(@ModelAttribute(name = "config") ExpenseSettings settings , Authentication authentication , BindingResult result , Model model) {
        if (!AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")){
            return "error/access-denied";
        }
        expenseSettingsService.updateExpenseSettings(settings);
        return "redirect:/manager/settings/expense";
    }
}