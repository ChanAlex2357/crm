package site.easy.to.build.crm.entity.api.request;

import lombok.Data;

@Data
public class AuthRequest {
    String username;
    String password;
    String email;
}
