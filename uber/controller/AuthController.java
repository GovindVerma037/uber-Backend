package com.project.uber.controller;

import com.project.uber.entity.User;
import com.project.uber.jwt.JwtReq;
import com.project.uber.jwt.JwtResp;
import com.project.uber.payload.ApiResponse;
import com.project.uber.services.AuthService;
import com.project.uber.services.UserService;
import com.project.uber.util.MailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


 @Autowired
    MailService mailService;

    @Autowired
    private UserService userService;


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody User user) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "User registered successfully",
                        userService.register(user),
                        "/api/auth/register",
                        HttpStatus.CREATED
                )
                );


    }

    @GetMapping("/login")
    public  ResponseEntity<ApiResponse<JwtResp>> loginUser(@RequestBody JwtReq jwtReq) {

       String token = authService.login(jwtReq);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse<>(
                                true,
                                "User login successfully",
                          new JwtResp(token),
                                "/api/auth/login",
                                HttpStatus.ACCEPTED
                        )
                );
    }

    @PostMapping("/forgot-password")
    public  ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam String email) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse<>(
                                true,
                                "otp send the register email l successfully",
                                authService.forgotPassword(email),
                                "/api/auth/forgot-password",
                                HttpStatus.ACCEPTED
                        )
                );
    }

    @PostMapping("/reset-password")
    public  ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam String email,@RequestParam String otp , @RequestParam String newPassword) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse<>(
                                true,
                                "reset password successfully",
                                authService.resetPassword(email,otp,newPassword),
                                "/api/auth/reset-password",
                                HttpStatus.ACCEPTED
                        )
                );
    }
}










//session absed




//    @Autowired
//    AuthenticationManager authenticationManager;
//@GetMapping("/login")
//public String loginUser(@RequestParam String email , @RequestParam String password , HttpServletRequest request) {
//    Authentication authentication =  authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(email,password)
//    );
//    if(authentication.isAuthenticated()){
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder .getContext());
//        return "Login Succesfull";
//    }
//    return "Invalid Credentials !!";
//}
