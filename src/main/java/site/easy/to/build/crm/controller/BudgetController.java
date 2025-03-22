package site.easy.to.build.crm.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.user.UserServiceImpl;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

@Controller
public class BudgetController {

    private final UserServiceImpl userService;

    private final AuthenticationUtils authenticationUtils;

    private final CustomerServiceImpl customerService;

    BudgetController(CustomerServiceImpl customerServiceImpl, AuthenticationUtils authenticationUtils, UserServiceImpl userServiceImpl) {
        this.customerService = customerServiceImpl;
        this.authenticationUtils = authenticationUtils;
        this.userService = userServiceImpl;
    }

    @GetMapping("/create-budget")
    public String createBudget(Model model, Authentication authentication) {
        // Verification des droits d'acces
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if (user.isInactiveUser()) {
            return "error/account-inactive";
        }
        // Preparation des donnees de formulaire
        List<Customer> customers;
        if (AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            customers = customerService.findAll();
        } else {
            customers = customerService.findByUserId(userId);
        }
        model.addAttribute("customers", customers);
        return "budget/create";
    }
}