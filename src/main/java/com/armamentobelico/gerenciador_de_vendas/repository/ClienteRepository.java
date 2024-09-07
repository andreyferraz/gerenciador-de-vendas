package com.armamentobelico.gerenciador_de_vendas.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armamentobelico.gerenciador_de_vendas.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    //consulta para buscar clientes pelo nome
    List<Cliente> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);

    //Consulta para filtrar clientes por país
    List<Cliente> findByPaisContainingIgnoreCase(String pais);

    //Consulta para listar clientes brasileiros
    List<Cliente> findByIsBrasileiroTrue();

    //Consulta para listar clientes estrangeiros
    List<Cliente> findByIsBrasileiroFalse();

}
