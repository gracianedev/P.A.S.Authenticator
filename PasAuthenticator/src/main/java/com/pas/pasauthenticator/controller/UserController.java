/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.controller;

import com.pas.pasauthenticator.model.Otp;
import com.pas.pasauthenticator.model.User;
import com.pas.pasauthenticator.service.OtpService;
import com.pas.pasauthenticator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author GFS_Mac
 */
@Controller
public class UserController {
    
    @Autowired
      private UserService userService;
    
    @Autowired
    private OtpService otpService;
    
    @GetMapping("/")
        public String email (Model model){
        model.addAttribute("user", new User());
        return "index";
                }
    
    @PostMapping("/enviar-email")
    public String sendCode (@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
        return "index";}
        int user_id = userService.findOrAddUser(userService.listUser(), user);
        Otp newOtp = otpService.createOtp();
        userService.addOtp(user_id, newOtp);
        return "redirect:/login";
    }
    
}
