/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.elhajoui.sales.controllers;

import jakarta.validation.Valid;
import java.io.IOException;
import net.elhajoui.sales.entities.Sale;
import net.elhajoui.sales.services.CustomUserDetails;
import net.elhajoui.sales.services.SaleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author marwa
 */
@Controller
public class SaleController {
    @Autowired
    private SaleServiceImp saleServiceImp;
    
    @GetMapping("/my_sales")
    public String sales(Model model,
                        @RequestParam(name= "keyword", defaultValue = "")String keyword, 
                        @RequestParam(name= "page", defaultValue = "0")int page, 
                        @RequestParam(name= "size", defaultValue = "5") int size){
        
        CustomUserDetails loggedInUser = (CustomUserDetails) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
        
        Long userId = loggedInUser.getId();
    
        model.addAttribute( "saleslist", saleServiceImp.getSalesByAgent(userId,keyword, page, size).getContent() );
        model.addAttribute( "totalPages", new int[saleServiceImp.getSalesByAgent(userId,keyword, page, size).getTotalPages()] );
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "sales/sales";
    }
    
    @GetMapping("/sales/add_sale")
    public String teamAdd(Model model){
        model.addAttribute("sale", new Sale()); 
        return "sales/add_form";
    }
    
    @PostMapping(path = "/sales/save")
    public String saveTeam(@Valid Sale sale,@RequestParam("file") MultipartFile file,
                           Authentication auth, BindingResult bindingResult, Model model) throws IOException{
        if(bindingResult.hasErrors()) return "/sales/add_sale";
        saleServiceImp.uploadSale(file, sale.getContractId(), auth.getName());
        model.addAttribute("successMessage", "Sale uploaded successfully!");
        return "redirect:/my_sales";
    }
    
}
