package com.armamentobelico.gerenciador_de_vendas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.armamentobelico.gerenciador_de_vendas.models.Cliente;
import com.armamentobelico.gerenciador_de_vendas.services.ClienteService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Página para visualizar todos os clientes
    @GetMapping
    public String viewAllClientes(Model model){
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "clientes-list";
    }

    // Página para criar um novo cliente
    @GetMapping("/novo")
    public String showCreateClienteForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "create-cliente";
    }

    // Método para processar a criação de um novo cliente
    @PostMapping("/novo")
    public String createCliente(@ModelAttribute Cliente cliente){
        clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

}
