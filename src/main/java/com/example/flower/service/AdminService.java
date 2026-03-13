package com.example.flower.service;

import com.example.flower.model.Admin;
import com.example.flower.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 🔐 LOGIN
    public String login(String email, String password) {

        if(email == null || password == null){
            return "Email and password required";
        }

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return "Login Successful";
        }

        return "Invalid Credentials";
    }


    // 👤 CREATE ADMIN
    public String createAdmin(String email, String password){

        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            return "Invalid email format";
        }

        if(adminRepository.findByEmail(email).isPresent()){
            return "Admin already exists";
        }

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(encoder.encode(password));

        adminRepository.save(admin);

        return "Admin Created Successfully";
    }


    // 📩 SEND OTP
    public String sendOtp(String email){

        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            return "Invalid email format";
        }

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if(admin == null){
            return "If the email exists, OTP has been sent";
        }

        SecureRandom random = new SecureRandom();
        String otp = String.valueOf(random.nextInt(900000) + 100000);

        admin.setOtp(otp);
        admin.setOtpExpiry(System.currentTimeMillis() + 5 * 60 * 1000);

        adminRepository.save(admin);

        sendEmail(email, otp);

        return "OTP sent successfully";
    }


    // 📧 SEND EMAIL USING RESEND API
    private void sendEmail(String toEmail, String otp){

        try{

            String apiKey = "re_D9wAhVpm_6Xn5L1UiTaSet2qbhseKiRd4";   // 🔑 yaha apna resend api key paste karo

            URL url = new URL("https://api.resend.com/emails");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);

            String jsonInput = "{"
                    + "\"from\":\"onboarding@resend.dev\","
                    + "\"to\":\"" + toEmail + "\","
                    + "\"subject\":\"Flower Admin OTP\","
                    + "\"html\":\"<h2>Your OTP is: " + otp + "</h2><p>This OTP expires in 5 minutes.</p>\""
                    + "}";

            try(OutputStream os = conn.getOutputStream()){
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            conn.getResponseCode();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    // 🔁 VERIFY OTP & RESET PASSWORD
    public String verifyOtp(String email, String otp, String newPassword){

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if(admin == null){
            return "Admin not found";
        }

        if(admin.getOtp() == null || !admin.getOtp().equals(otp)){
            return "Invalid OTP";
        }

        if(System.currentTimeMillis() > admin.getOtpExpiry()){
            return "OTP Expired";
        }

        admin.setPassword(encoder.encode(newPassword));
        admin.setOtp(null);
        admin.setOtpExpiry(0);

        adminRepository.save(admin);

        return "Password reset successful";
    }

}