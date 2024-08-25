package com.roupas.app.repository;

import com.roupas.app.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByRegistration(String registrationNumber);

    // Metodos automáticos
    List<Funcionario> findByName(String name);
    List<Funcionario> findByRegistration(String registration);

    //Metodo do jpql
    @Query("SELECT f FROM Funcionario f WHERE f.age >= :age")
    List<Funcionario> findByAgeGreaterThanEqual(@Param("age") int age);
}
