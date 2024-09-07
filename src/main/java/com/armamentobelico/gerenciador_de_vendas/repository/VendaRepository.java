package com.armamentobelico.gerenciador_de_vendas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armamentobelico.gerenciador_de_vendas.models.Venda;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, UUID> {

    // Consulta para listar vendas por status de envio
    List<Venda> findByEnviada(Boolean enviada);

}
