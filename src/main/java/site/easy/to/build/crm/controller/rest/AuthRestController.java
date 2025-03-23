package site.easy.to.build.crm.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.api.request.AuthRequest;
import site.easy.to.build.crm.entity.api.response.ApiResponse;
import site.easy.to.build.crm.entity.api.response.AuthResponse;
import site.easy.to.build.crm.service.user.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody AuthRequest entity) {
        try {
            User user = userService.findByUsername(entity.getUsername()).get(0);
            AuthResponse authResponse = new AuthResponse(user);
            if (!passwordEncoder.matches(entity.getPassword(), user.getPassword())) {
                authResponse.setToken(null);
                authResponse.setEmail(null);
                return ResponseEntity.ok(ApiResponseBuilder.success("auth error",authResponse));
            }
            return ResponseEntity.ok(ApiResponseBuilder.success("auth success",authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500("auth error",e));
        }
    }
}
