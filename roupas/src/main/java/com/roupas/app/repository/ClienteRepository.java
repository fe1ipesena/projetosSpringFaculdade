package com.roupas.app.repository;

import com.roupas.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Metodos automáticos
    List<Cliente> findByName(String name);
    List<Cliente> findByCpf(String cpf);

    //Metodo com JPQL (achando cliente por idade)
    @Query("SELECT c FROM Cliente c WHERE c.age = :age")
    List<Cliente> findByAge(@Param("age") int age);
}
