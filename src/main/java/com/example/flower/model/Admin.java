package com.example.flower.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin {

    @Id
    private String id;

    @Indexed(unique = true) // email duplicate na ho
    private String email;

    private String password;

    private String otp;
    private long otpExpiry;

    // Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(long otpExpiry) {
        this.otpExpiry = otpExpiry;
    }
}