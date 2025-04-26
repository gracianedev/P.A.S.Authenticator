/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author GFS_Mac
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public void enviarCodigoVerificacao(String destinatario, String codigo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(env.getProperty("spring.mail.username")); // remetente
        mensagem.setTo(destinatario);
        mensagem.setSubject("Código de Verificação - P.A.S.");
        mensagem.setText("Olá!\n\nSeu código de verificação é: " + codigo + "\n\nEquipe P.A.S.");

        mailSender.send(mensagem);
    }
}
