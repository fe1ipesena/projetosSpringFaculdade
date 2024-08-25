package com.roupas.app.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

                    //nome com validation
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+( [A-Za-zÀ-ÖØ-öø-ÿ]+)+$", message = "O nome deve conter pelo menos duas palavras separadas por um espaço")
    @NotBlank
    private String name;

                //idade com validacao de minimo
    @Min(value=14, message = "A idade minima do funcionario precisa ser 14 anos.")
    @NotNull
    private int age;

                    //matricula do funcionario
    @NotBlank
    @Column(unique = true) // funciona como uma 2 garantia para ser unico
    private String registration;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Venda> sales;

}
