package com.armamentobelico.gerenciador_de_vendas.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armamentobelico.gerenciador_de_vendas.exceptions.ClienteNotFoundException;
import com.armamentobelico.gerenciador_de_vendas.models.Cliente;
import com.armamentobelico.gerenciador_de_vendas.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(UUID id){
        return clienteRepository.findById(id)
        .orElseThrow(()-> new ClienteNotFoundException("Cliente com ID" + id + "não encontradi"));
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(UUID id, Cliente cliente) {
        Cliente existingCliente = getClienteById(id);
        existingCliente.setNomeCompleto(cliente.getNomeCompleto());
        existingCliente.setEndereco(cliente.getEndereco());
        existingCliente.setPais(cliente.getPais());
        existingCliente.setIsBrasileiro(cliente.getIsBrasileiro());
        existingCliente.setContato(cliente.getContato());
        return clienteRepository.save(existingCliente);
    }

    public void deleteCliente(UUID id) {
        Cliente cliente = getClienteById(id);
        clienteRepository.delete(cliente);
    }

    // Métodos de filtragem
    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }

    public List<Cliente> findByPais(String pais) {
        return clienteRepository.findByPaisContainingIgnoreCase(pais);
    }

    public List<Cliente> findNacionais() {
        return clienteRepository.findByIsBrasileiroTrue();
    }

    public List<Cliente> findInternacionais() {
        return clienteRepository.findByIsBrasileiroFalse();
    }
}
