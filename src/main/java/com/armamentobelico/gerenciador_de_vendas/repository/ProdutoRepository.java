package com.armamentobelico.gerenciador_de_vendas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armamentobelico.gerenciador_de_vendas.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
