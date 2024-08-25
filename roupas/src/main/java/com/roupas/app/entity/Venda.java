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
    @JoinColumn(name = "customer_id")
    @NotNull(message = "O cliente não pode ser nulo.")
    @JsonIgnoreProperties("purchases")
    private Cliente customer;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "employee_id")
    @NotNull(message = "O funcionário não pode ser nulo.")
    @JsonIgnoreProperties("sales")
    private Funcionario employee;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnoreProperties("sales")
    private List<Produto> products;
}
