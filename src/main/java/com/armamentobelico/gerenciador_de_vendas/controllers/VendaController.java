package com.armamentobelico.gerenciador_de_vendas.controllers;

import com.armamentobelico.gerenciador_de_vendas.models.Venda;
import com.armamentobelico.gerenciador_de_vendas.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<Venda>> getAllVendas() {
        List<Venda> vendas = vendaService.getAllVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable UUID id) {
        Venda venda = vendaService.getVendaById(id);
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody Venda venda) {
        Venda newVenda = vendaService.createVenda(venda);
        return new ResponseEntity<>(newVenda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable UUID id, @RequestBody Venda venda) {
        Venda updatedVenda = vendaService.updateVenda(id, venda);
        return new ResponseEntity<>(updatedVenda, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable UUID id) {
        vendaService.deleteVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Venda>> getVendasByStatus(@RequestParam Boolean enviada) {
        return new ResponseEntity<>(vendaService.findByEnviada(enviada), HttpStatus.OK);
    }
}
