package com.armamentobelico.gerenciador_de_vendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armamentobelico.gerenciador_de_vendas.exceptions.ProdutoNotFoundException;
import com.armamentobelico.gerenciador_de_vendas.models.Produto;
import com.armamentobelico.gerenciador_de_vendas.repository.ProdutoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto com ID " + id + " não encontrado"));
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(UUID id, Produto produto) {
        Produto existingProduto = getProdutoById(id);
        existingProduto.setNome(produto.getNome());
        return produtoRepository.save(existingProduto);
    }

    public void deleteProduto(UUID id) {
        Produto produto = getProdutoById(id);
        produtoRepository.delete(produto);
    }
}
