package site.easy.to.build.crm.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.builder.ApiResponseBuilder;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.api.ApiResponse;
import site.easy.to.build.crm.entity.api.request.AuthRequest;
import site.easy.to.build.crm.service.user.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody AuthRequest entity) {
        try {
            User user = userService.findByUsername(entity.getUsername()).get(0);
            return ResponseEntity.ok(ApiResponseBuilder.success("auth success", user));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseBuilder.success("auth error",e));
        }
    }
}
