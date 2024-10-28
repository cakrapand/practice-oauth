package org.example.practiceoauth.user;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping()
    public Map<String, Object> getUser(JwtAuthenticationToken principal) {
        return principal.getTokenAttributes();
    }
}
