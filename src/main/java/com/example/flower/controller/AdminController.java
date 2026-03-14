package com.example.flower.controller;

import com.example.flower.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {


    @Autowired
    private AdminService adminService;

    // 🔐 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        if(email == null || password == null){
            return "Email and password required";
        }

        return adminService.login(email, password);
    }

    // 👤 CREATE ADMIN (use only once)
    @PostMapping("/create")
    public String create(@RequestBody Map<String,String> request){

        String email = request.get("email");
        String password = request.get("password");

        if(email == null || password == null){
            return "Email and password required";
        }

        return adminService.createAdmin(email, password);
    }

    // 📩 SEND OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody Map<String,String> request){

        String email = request.get("email");

        if(email == null){
            return "Email required";
        }

        return adminService.sendOtp(email);
    }

    // 🔁 RESEND OTP
    @PostMapping("/resend-otp")
    public String resendOtp(@RequestBody Map<String,String> request){

        String email = request.get("email");

        if(email == null){
            return "Email required";
        }

        return adminService.sendOtp(email);
    }

    // 🔁 VERIFY OTP & RESET PASSWORD
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String,String> request){

        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        if(email == null || otp == null || newPassword == null){
            return "Email, OTP and new password required";
        }

        return adminService.verifyOtp(email, otp, newPassword);
    }


}
