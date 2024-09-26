package com.armamentobelico.gerenciador_de_vendas.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armamentobelico.gerenciador_de_vendas.models.Cliente;
import com.armamentobelico.gerenciador_de_vendas.services.ClienteService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Método para visualizar todos os clientes
    @GetMapping
    public String viewAllClientes(Model model) {
        List<Cliente> clientes = clienteService.getAllClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("totalClientes", clientes.size()); // Adiciona total de clientes
        return "clientes-list"; // Retorna para a mesma página
    }

    // Método para pesquisar clientes
    @GetMapping("/search")
    public String searchClientes(@RequestParam(required = false) String nome,
                                 @RequestParam(required = false) String pais,
                                 Model model) {
        List<Cliente> clientes;
        int quantidadeFiltrados = 0; // Inicializa a contagem
    
        if (nome != null && !nome.isEmpty()) {
            clientes = clienteService.findByNome(nome);
        } else if (pais != null && !pais.isEmpty()) {
            clientes = clienteService.findByPais(pais);
        } else {
            clientes = clienteService.getAllClientes(); // Se nada for informado, retorna todos os clientes
        }
    
        quantidadeFiltrados = clientes.size(); // Atualiza a contagem de clientes filtrados
    
        model.addAttribute("clientes", clientes);
        model.addAttribute("quantidadeFiltrados", quantidadeFiltrados); // Adiciona total de clientes filtrados
        return "clientes-list"; // Retorna para a mesma página
    }
    

    @GetMapping("/nacionais")
    public String viewClientesNacionais(Model model) {
        List<Cliente> clientesNacionais = clienteService.findNacionais();
        int quantidadeNacionais = clientesNacionais.size(); // Conta a quantidade de clientes nacionais
        model.addAttribute("clientes", clientesNacionais);
        model.addAttribute("quantidadeNacionais", quantidadeNacionais); // Adiciona a quantidade ao modelo
        return "clientes-list"; // Retorna para a mesma página
    }
    
    @GetMapping("/internacionais")
    public String viewClientesInternacionais(Model model) {
        List<Cliente> clientesInternacionais = clienteService.findInternacionais();
        int quantidadeInternacionais = clientesInternacionais.size(); // Conta a quantidade de clientes internacionais
        model.addAttribute("clientes", clientesInternacionais);
        model.addAttribute("quantidadeInternacionais", quantidadeInternacionais); // Adiciona a quantidade ao modelo
        return "clientes-list"; // Retorna para a mesma página
    }
    

    // Página para visualizar os detalhes de um cliente
    @GetMapping("/{id}")
    public String viewClienteDetails(@PathVariable UUID id, Model model) {
        Cliente cliente = clienteService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("historicoCompras", cliente.getHistoricoCompras()); // Adicione esta linha
        return "cliente-detalhes";
    }

    // Página para criar um novo cliente
    @GetMapping("/novo")
    public String showCreateClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "create-cliente";
    }

    // Página para editar um cliente
    @GetMapping("/editar/{id}")
    public String showEditClienteForm(@PathVariable UUID id, Model model) {
        Cliente cliente = clienteService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "editar-cliente";
    }

    // Método para processar a criação de um novo cliente
    @PostMapping("/novo")
    public String createCliente(@ModelAttribute Cliente cliente) {
        clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

    // Método para processar a edição de um cliente
    @PostMapping("/{id}")
    public String updateCliente(@PathVariable UUID id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteService.updateCliente(cliente);
        return "redirect:/clientes";
    }

    // Método para excluir um cliente
    @GetMapping("/deletar/{id}")
    public String deleteCliente(@PathVariable UUID id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }

}
