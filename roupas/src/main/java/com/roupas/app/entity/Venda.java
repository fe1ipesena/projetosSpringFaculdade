package com.roupas.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O endereço de entrega não pode estar vazio.")
    private String deliveryAdress;

    private BigDecimal totalValue;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente não pode ser nulo.")
    @JsonManagedReference
    //@JsonIgnoreProperties("compras")
    private Cliente cliente;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "funcionario_id")
    @NotNull(message = "O funcionário não pode ser nulo.")
    //@JsonIgnoreProperties("vendas")
    @JsonManagedReference
    private Funcionario funcionario;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    //@JsonIgnoreProperties("vendas")
    @JsonManagedReference
    private List<Produto> produtos;
}
