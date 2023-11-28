package user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_api.dto.UserDto;
import user_api.service.authService.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@PreAuthorize("isAnonymous()")
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> register (@RequestBody UserDto userDto){
        String token = authService.register(userDto);
        if (token != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        }
        return ResponseEntity.status(409).body("Username is taken!");
    }
    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<String> authenticate (@RequestBody UserDto userDto){
        try {
            String token = authService.authenticate(userDto);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e){
            return  ResponseEntity.internalServerError().body("smt is wrong... Sorry)");
        }
    }
}
