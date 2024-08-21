package com.uniamerica.carros.app.service;


import com.uniamerica.carros.app.entity.Acessorio;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.repository.AcessorioRepository;
import com.uniamerica.carros.app.repository.CarroRepository;
import com.uniamerica.carros.app.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AcessorioService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;

    public Acessorio save(Acessorio acessorio) {
        return acessorioRepository.save(acessorio);
    }

    public Acessorio findById(long id) {
        return acessorioRepository.findById(id).get();
    }

    public List<Acessorio> findAll() {
        return acessorioRepository.findAll();
    }

    public void deleteById(long id) {
        acessorioRepository.deleteById(id);
    }

    public Acessorio updateById(long id, Acessorio updatedAcessorio){
        Optional<Acessorio> op = acessorioRepository.findById(id);
        if(op.isPresent()){
            Acessorio existingAcessorio = op.get();

            if(updatedAcessorio.getNome() != null){
                existingAcessorio.setNome(updatedAcessorio.getNome());
            }
            return acessorioRepository.save(existingAcessorio);
        }
        throw new RuntimeException("Acessorio com ID " + id + " não encontrado");
    }

}
