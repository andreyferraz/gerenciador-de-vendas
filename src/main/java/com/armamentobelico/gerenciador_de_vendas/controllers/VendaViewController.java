package com.armamentobelico.gerenciador_de_vendas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.armamentobelico.gerenciador_de_vendas.services.VendaService;

@Controller
@RequestMapping("/vendas")
public class VendaViewController {

    @Autowired
    private VendaService vendaService;

    @org.springframework.web.bind.annotation.InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String viewAllVendas(Model model){
        model.addAttribute("vendas", vendaService.getAllVendas());
        return "vendas-list";
    }
}
