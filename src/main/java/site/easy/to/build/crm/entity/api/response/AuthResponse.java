package site.easy.to.build.crm.entity.api.response;

import lombok.Data;
import site.easy.to.build.crm.entity.User;

@Data
public class AuthResponse {
    String username;
    String token;
    String email;

    public AuthResponse (String username, String token, String email) {
        this.username = username;
        this.token = token;
        this.email = email;
    }

    public AuthResponse () {}
    public AuthResponse(User user) {
        setUsername(user.getUsername());
        setToken(user.getToken());
        setEmail(user.getEmail());
    }
}
