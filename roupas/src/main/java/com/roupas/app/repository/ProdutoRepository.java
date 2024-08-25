package com.roupas.app.repository;

import com.roupas.app.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Metodos automáticos
    List<Produto> findByProduct(String product);
    List<Produto> findByValueBetween(BigDecimal minValue, BigDecimal maxValue);

    // Metodo do JPQL
    @Query("SELECT p FROM Produto p WHERE p.value > :value")
    List<Produto> findByValueGreaterThan(@Param("value") BigDecimal value);
}
