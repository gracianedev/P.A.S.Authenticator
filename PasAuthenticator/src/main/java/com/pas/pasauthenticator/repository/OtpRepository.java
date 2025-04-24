/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pas.pasauthenticator.repository;

import com.pas.pasauthenticator.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author GFS_Mac
 */
@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer>{
    
}
