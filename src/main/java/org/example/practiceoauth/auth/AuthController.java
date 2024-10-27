package org.example.practiceoauth.auth;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(){
        return "This should return apaya";
    }

    @PostMapping("/logout")
    public String logout(){
        return "This should return apaya";
    }

    @PostMapping("/refresh-token")
    public String refreshToken(){
        return "This should return refresh token";
    }

}
