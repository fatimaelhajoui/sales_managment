/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package net.elhajoui.sales.repositories;

import net.elhajoui.sales.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marwa
 */
public interface TeamRepository extends JpaRepository<Team ,Long>{
    Page<Team> findByNameContaining(String keyword, Pageable page);
}
