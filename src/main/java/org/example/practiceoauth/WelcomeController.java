package org.example.practiceoauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

//    @GetMapping("/")
//    public String welcome(@AuthenticationPrincipal OAuth2User principal) {
//        return "Welcome " + principal.getAttribute("name");
//    }

    @GetMapping("/api/v1/welcome")
    public ResponseEntity<BaseResponse<String>> welcome() {
        return ResponseEntity.ok(new BaseResponse<>(true, "OK", "Welcomeee"));
    }
}
