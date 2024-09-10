package com.armamentobelico.gerenciador_de_vendas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.armamentobelico.gerenciador_de_vendas.models.Produto;
import com.armamentobelico.gerenciador_de_vendas.services.ProdutoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoViewController {

    @Autowired
    private ProdutoService produtoService;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Página para visualizar todos os produtos
    @GetMapping
    public String viewAllProdutos(Model model){
        model.addAttribute("produtos", produtoService.getAllProdutos());
        return "produtos-list";
    }

    // Página para cadastrar um novo produto
    @GetMapping("/novo")
    public String showCreateProdutoForm(Model model){
        model.addAttribute("produto", new Produto());
        return "create-produto";
    }

    // Método para cadastrar um novo produto
    @PostMapping("/novo")
    public String createProduto(@ModelAttribute Produto produto){
        produtoService.createProduto(produto);
        return "redirect:/produtos";
    }

    // Página para editar um produto existente
    @GetMapping("/editar/{id}")
    public String showEditProdutoForm(@PathVariable UUID id, Model model){
        Produto produto = produtoService.getProdutoById(id);
        model.addAttribute("produto", produto);
        return "editar-produto";
    }

    // Método para processar a edição de um produto
    @PostMapping("/{id}")
    public String updateProduto(@PathVariable UUID id, @ModelAttribute Produto produto){
        produto.setId(id);
        produtoService.updateProduto(id, produto);
        return "redirect:/produtos";
    }

}
