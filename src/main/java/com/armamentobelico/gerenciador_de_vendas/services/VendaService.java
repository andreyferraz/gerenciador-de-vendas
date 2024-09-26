package com.armamentobelico.gerenciador_de_vendas.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armamentobelico.gerenciador_de_vendas.exceptions.VendaNotFoundException;
import com.armamentobelico.gerenciador_de_vendas.models.ProdutoQuantidade;
import com.armamentobelico.gerenciador_de_vendas.models.Venda;
import com.armamentobelico.gerenciador_de_vendas.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    
    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Venda getVendaById(UUID id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new VendaNotFoundException("Venda com ID " + id + " não encontrada"));
    }

    public Venda createVenda(Venda venda) {
        // Validação das quantidades dos produtos
        for (ProdutoQuantidade pq : venda.getProdutos()) {
            if (pq.getQuantidade() == null || pq.getQuantidade() < 1) {
                throw new IllegalArgumentException("Quantidade do produto não pode ser menor que 1");
            }
        }
        return vendaRepository.save(venda);
    }

    public Venda updateVenda(UUID id, Venda venda) {
        Venda existingVenda = getVendaById(id);
        
        // Atualiza os produtos e as quantidades
        existingVenda.setProdutos(venda.getProdutos());
        
        // Removido a atualização de quantidade direta, pois agora faz parte de ProdutoQuantidade
        existingVenda.setDataCompra(venda.getDataCompra());
        existingVenda.setEnviada(venda.getEnviada());
        return vendaRepository.save(existingVenda);
    }

    public void deleteVenda(UUID id) {
        Venda venda = getVendaById(id);
        vendaRepository.delete(venda);
    }

    public List<Venda> findByEnviada(Boolean enviada) {
        return vendaRepository.findByEnviada(enviada);
    }
}

