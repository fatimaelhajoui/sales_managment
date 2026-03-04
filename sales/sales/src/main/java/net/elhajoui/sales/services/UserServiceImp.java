/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.elhajoui.sales.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.elhajoui.sales.abstracts.UserService;
import net.elhajoui.sales.entities.AppUser;
import net.elhajoui.sales.entities.Team;
import net.elhajoui.sales.repositories.TeamRepository;
import net.elhajoui.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author marwa
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Page<AppUser> AllUsers(String keyword,int page, int size) {
        Page<AppUser> Users= userRepository.findByUsernameContaining(keyword, PageRequest.of(page, size));
        return Users;
    }

    @Override
    public AppUser createAppUser(AppUser appUser) {
        AppUser new_appUser = new AppUser();
        Team team= null;
        
        // Check if email already exists
        if (userRepository.existsByMail(appUser.getMail())) {
        throw new RuntimeException("Email '" + appUser.getMail() + "' is already in use");
        }

        team = teamRepository.findById(appUser.getTeam().getId())
        .orElseThrow(() -> new RuntimeException(
            "Team with id " + appUser.getTeam().getId() + " not found"
        ));
        appUser.setUsername(appUser.getUsername());
        appUser.setMail(appUser.getMail());
        appUser.setPassword(appUser.getPassword());
        appUser.setRole(appUser.getRole());
        appUser.setStatus(appUser.getStatus());
        appUser.setTeam(team);
        
        userRepository.save(appUser);
        
        return appUser;
   }

    @Override
    public AppUser editAppUser(Long appUser_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppUser updateAppUser(Long appUser_id, AppUser appUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAppUser(Long appUser_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
