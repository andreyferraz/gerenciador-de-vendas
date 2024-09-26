package com.armamentobelico.gerenciador_de_vendas.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produto_quantidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoQuantidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // Adicione um campo ID

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull
    @Min(1)
    private Integer quantidade;
}

