package com.armamentobelico.gerenciador_de_vendas.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;

    @Column(name = "endereco", nullable = false)
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @Column(name = "pais", nullable = false)
    @NotBlank(message = "País é obrigatório")
    private String pais;

    @Column(name = "isbrasileiro", nullable = false)
    @NotNull(message = "É obrigatório informar se é brasileiro ou não")
    private Boolean isBrasileiro;

    @Column(name = "contato", nullable = false)
    @NotBlank(message = "Contato é obrigatório")
    private String contato;

    @OneToMany(mappedBy = "cliente", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Venda> historicoCompras;

}
