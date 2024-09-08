package com.armamentobelico.gerenciador_de_vendas.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tb_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Gera UUID automaticamente
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nomeCompleto;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "isbrasileiro", nullable = false)
    private Boolean isBrasileiro;

    @Column(name = "contato", nullable = false)
    private String contato;

    @OneToMany(mappedBy = "cliente", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Venda> historicoCompras;

}
