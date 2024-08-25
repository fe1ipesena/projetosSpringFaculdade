package com.roupas.app.repository;

import com.roupas.app.entity.Cliente;
import com.roupas.app.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByCliente(Cliente cliente);

    List<Venda> findByDeliveryAdress(String deliveryAdress);
    List<Venda> findByTotalValueGreaterThan(BigDecimal totalValue);

    // Metodo do jpql
    @Query("SELECT v FROM Venda v WHERE v.totalValue BETWEEN :minValue AND :maxValue")
    List<Venda> findByTotalValueBetween(@Param("minValue") BigDecimal minValue, @Param("maxValue") BigDecimal maxValue);

}
