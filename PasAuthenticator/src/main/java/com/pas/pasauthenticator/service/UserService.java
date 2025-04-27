/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.service;

import com.pas.pasauthenticator.model.Otp;
import com.pas.pasauthenticator.model.User;
import com.pas.pasauthenticator.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GFS_Mac
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private OtpService otpService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public User findUserWithOtps(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Força o carregamento da lista enquanto ainda está dentro da sessão Hibernate
        user.getOtpList().size();

        return user;
    }

    public User addOtp(int user_id, Otp newOtp) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.addOtp(newOtp);
        return userRepository.save(user);
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }

    public int findOrAddUser(List<User> users, User email) {
        for (User user : users) {
            if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email.getEmail())) {
                return user.getId(); // Usuário já existe
            }
        }

        // Se não encontrar nenhum usuário com o mesmo email cria um novo
        User newUser = new User();
        newUser.setEmail(email.getEmail());
        userRepository.save(newUser);
        return newUser.getId();
    }

    public boolean hasValidOtp(User user) {
        List<Otp> otps = user.getOtpList();
        if (otps == null || otps.isEmpty()) {
            return false;
        }

        Otp lastOtp = otps.get(otps.size() - 1);
        return otpService.isValidTime(lastOtp.getTimestamp());
    }

}
