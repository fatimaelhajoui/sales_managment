/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.elhajoui.sales.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author marwa
 */
@Entity(name = "app_admin")
@Table(name = "app_admin")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString 
public class AppAdmin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 100, message = "username must be between 2 and 100 characters")
    private String fname;
    
    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 100, message = "username must be between 2 and 100 characters")
    private String lname;
        
    @NotEmpty(message = "Username is required")
    @Size(min = 2, max = 100, message = "username must be between 2 and 100 characters")
    private String username;
    
    @NotEmpty(message = "password is required")
    @Size(min = 4, max = 255, message = "password must be between 4 and 255 characters")
    private String password;
    
    
    @NotEmpty(message = "email is required")
    @Column(name = "email", nullable = false, unique = true)
    @Email(message="invalid email format")
    private String mail;
    
    @NotEmpty(message = "role is required")
    private String role="ADMIN";
}
