package com.uniamerica.carros.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.carros.app.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}




