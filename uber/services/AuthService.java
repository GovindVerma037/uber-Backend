package com.project.uber.services;

import com.project.uber.entity.User;
import com.project.uber.jwt.JwtReq;
import com.project.uber.jwt.JwtResp;
import com.project.uber.repository.UserRepository;
import com.project.uber.util.JwtUtil;
import com.project.uber.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
   @Autowired
   private MailService mailService;
   @Autowired
   private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;
    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private  final Map<String,String> otpStorage = new HashMap<>();

    public String login(JwtReq jwtReq) {
 try{
     Authentication authentication = authenticationManager.authenticate(
             new org.springframework.security.authentication
                     .UsernamePasswordAuthenticationToken(
                     jwtReq.getUsername(), jwtReq.getPassword()
             )
     );
     return jwtUtil.generateToken(jwtReq.getUsername());

 } catch (Exception e) {
     throw new BadCredentialsException("Invalid username or password");

 }

    }

    public String forgotPassword(String email) {
      if( userRepository.findByEmail(email) != null){
          String otp = generateOTP(email);
          mailService.sendEmail(email,"forgot password OTP","Your OTP for password reset is: " + otp);
          return "OTP sent to email";
      }else{
          throw new RuntimeException("Email not found");
      }

    }

    public String resetPassword(String email, String otp, String newPassword) {
        if(otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)){
            User user =  userRepository.findByEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
            otpStorage.remove(email);
            return "Password reset successful";
        }else{
          throw new RuntimeException("Invalid OTP or email");
        }
    }

    public String  generateOTP(String email) {
        // Generate a random 6-digit OTP
        int otp = (int) (Math.random() * 900000) + 100000; // new Random().nextInt(999999)
        otpStorage.put(email,String.valueOf(otp));
        return String.valueOf(otp);
    }
}
