package site.easy.to.build.crm.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.Role;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.api.request.AuthRequest;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.entity.api.response.AuthResponse;
import site.easy.to.build.crm.service.user.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthRestController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;

    
    @PostMapping
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody AuthRequest entity) {
        try {
            System.out.println(entity);
            List<User> users = userService.findByUsername(entity.getUsername());
            if (users.size() == 0) {
                log.info("auth-error" +" : Invalid login");
                return ResponseEntity.ok(ApiResponseBuilder.success("auth error : User not found",null));
            }

            User user = users.get(0);
            AuthResponse authResponse = new AuthResponse(user); 
            Role role = user.getRoles().get(0);

            log.info("Role founded" ,role);
            if (role.getName().equals("ROLE_MANAGER")) {
                log.info("auth-success");
                return ResponseEntity.ok(ApiResponseBuilder.success("auth success",authResponse));
            }

            authResponse.setToken(null);
            authResponse.setEmail(null);
            log.info("auth-error" +" : Invalid login");
            return ResponseEntity.ok(ApiResponseBuilder.success("auth error",authResponse));
        } catch (Exception e) {
            log.error("auth-error", e);
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("auth error : User not found",e));
        }
    }
}
