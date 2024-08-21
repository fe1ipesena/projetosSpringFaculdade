package biblioteca.bibliotecaApp.entity;

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

    @NotBlank
    private String observacao;

    @OneToMany
    @NotEmpty(message = "A lista de titulos nao pode ser vazia")
    private List<Titulo> titulos;

    @ManyToOne
    @NotNull(message = "A venda precisa ter um cliente vinculado")
    private Cliente cliente;

    @NotNull(message = "O valor total é obrigatório")
    private BigDecimal valorTotal;
}
