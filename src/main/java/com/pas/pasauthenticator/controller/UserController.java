/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.controller;

import com.pas.pasauthenticator.model.Otp;
import com.pas.pasauthenticator.model.User;
import com.pas.pasauthenticator.service.EmailService;
import com.pas.pasauthenticator.service.OtpService;
import com.pas.pasauthenticator.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String email(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("otp", new Otp());
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/bem-vindo")
    public String sucesso() {
        return "bem-vindo";
    }

    @PostMapping("/enviar-email")
    public String sendCode(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "index";
        }
        try {
        int user_id = userService.findOrAddUser(userService.listUser(), user);
        Otp newOtp = otpService.createOtp();
        userService.addOtp(user_id, newOtp);
        emailService.enviarCodigoVerificacao(user.getEmail(), newOtp.getCode());

        User fullUser = userService.findUserWithOtps(user_id);
        session.setAttribute("user", fullUser);

        redirectAttributes.addFlashAttribute("sucesso", "Código enviado com sucesso para o e-mail informado.");
        return "redirect:/login";
    }  catch (Exception e) {
        redirectAttributes.addFlashAttribute("erro", "Falha ao enviar o código. Tente novamente mais tarde.");
        return "redirect:/";
    }
    }

    @PostMapping("/autenticacao")
    public String verify(@ModelAttribute("otp") Otp otp, HttpSession session, SessionStatus status, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        String codeInput = otp.getCode();

        if (user == null) {
            redirectAttributes.addFlashAttribute("erro", "Sessão expirada. Por favor, faça login novamente.");
            return "redirect:/";
        }

        if (!otpService.isValidOtpUser(user, codeInput)) {
            redirectAttributes.addFlashAttribute("erro", "Código inválido. Tente novamente.");
            return "redirect:/login";
        }

        if (!otpService.isValidTime(user.getOtpTime())) {
            redirectAttributes.addFlashAttribute("erro", "Código expirado. Solicite um novo código.");
            return "redirect:/";
        }

        status.setComplete();
        return "redirect:/bem-vindo";
    }

}


