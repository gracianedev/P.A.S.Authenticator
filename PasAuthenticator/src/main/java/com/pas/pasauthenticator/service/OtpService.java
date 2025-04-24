/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.service;

import com.pas.pasauthenticator.model.Otp;
import com.pas.pasauthenticator.model.User;
import com.pas.pasauthenticator.repository.OtpRepository;
import com.pas.pasauthenticator.repository.UserRepository;
import java.security.SecureRandom;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GFS_Mac
 */
@Service
public class OtpService {
    
    private static final int OTP_LENGTH = 6;
    private static final int EXPIRATION_TIME_IN_MINUTES = 5;  //código expira em 5 minutos
    

    
    @Autowired
    private OtpRepository otpRepository;
    
    @Autowired 
    private UserRepository userRepository;
    
    private User user;
    
    private Otp otp;
    
    public Otp createOtp (){
       String otpCode = generateOtpCode();
       Instant timestamp = Instant.now();

        return new Otp(otpCode, timestamp); 
    }
    
    public Otp generateOtp (int user_id){       
        
        // Gerar o OTP
        String otpCode = generateOtpCode();

        otp.setCode(otpCode); 
        user.addOtp(otp); // Adiciona o OTP ao usuário

        // Salvar o OTP no banco
        otpRepository.save(otp);

        // Retornar o OTP gerado
        return otp;
    }

    // Método para gerar o código OTP alfanumérico
    private String generateOtpCode() {
        StringBuilder otpCode = new StringBuilder(OTP_LENGTH);

        // Gerar caracteres aleatórios (letras maiúsculas, minúsculas e números)
    for (int i = 0; i < 6; i++) {
        otpCode.append(generateRandomChar());
    }

    return otpCode.toString();
}
    
    
private char generateRandomChar() {
    // Gerar um número aleatório entre 0 e 61
    int random = new SecureRandom().nextInt(62); // 26 letras maiúsculas + 26 letras minúsculas + 10 números

    // Se o número aleatório estiver entre 0 e 25, é uma letra maiúscula
    if (random < 26) {
        return (char) ('A' + random);
    }
    // Se estiver entre 26 e 51, é uma letra minúscula
    else if (random < 52) {
        return (char) ('a' + (random - 26));
    }
    // Caso contrário, é um número
    else {
        return (char) ('0' + (random - 52));
    }
}

// Método para verificar se o OTP ainda é válido
        public boolean isValid(Instant timestamp) {
            Instant expirationTime = timestamp.plusSeconds(EXPIRATION_TIME_IN_MINUTES * 60);
            return Instant.now().isBefore(expirationTime);
        }

}
