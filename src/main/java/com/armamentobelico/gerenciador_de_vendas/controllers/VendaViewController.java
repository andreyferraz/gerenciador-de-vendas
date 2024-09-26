package com.armamentobelico.gerenciador_de_vendas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.armamentobelico.gerenciador_de_vendas.models.Venda;
import com.armamentobelico.gerenciador_de_vendas.services.ClienteService;
import com.armamentobelico.gerenciador_de_vendas.services.ProdutoService;
import com.armamentobelico.gerenciador_de_vendas.services.VendaService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/vendas")
public class VendaViewController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public String viewAllVendas(Model model){
        model.addAttribute("vendas", vendaService.getAllVendas());
        return "vendas-list";
    }

    // Página para criar uma nova venda
    @GetMapping("/nova")
    public String showCreateVendaForm(Model model){
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("produtos", produtoService.getAllProdutos());
        return "create-venda";
    }

    // Método para processar a criação de uma nova venda
    @PostMapping("/nova")
    public String createVenda(@ModelAttribute Venda venda){
        vendaService.createVenda(venda);
        return "redirect:/vendas";
    }

}
