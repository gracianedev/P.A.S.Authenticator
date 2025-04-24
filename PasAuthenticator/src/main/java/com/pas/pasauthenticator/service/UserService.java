/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pas.pasauthenticator.service;

import com.pas.pasauthenticator.model.Otp;
import com.pas.pasauthenticator.model.User;
import com.pas.pasauthenticator.repository.UserRepository;
import java.util.List;
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

    public User saveUser(User user) {
        return userRepository.save(user);
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

    
}
