package com.uniamerica.carros.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public String save(Marca marca) {
        marcaRepository.save(marca);
        return "Salvo com sucesso.";
    }

    public Marca findById(long id) {
		return marcaRepository.findById(id).get();
    }

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca updateById(long id, Marca updatedMarca){
        Optional<Marca> op = marcaRepository.findById(id);
        if(op.isPresent()){
            Marca existingMarca = op.get();

            if(updatedMarca.getNome() != null){
                existingMarca.setNome(updatedMarca.getNome());
            }
            return marcaRepository.save(existingMarca);
        }
        throw new RuntimeException("Marca com ID " + id + " não encontrada");
    }

    public void deleteById(long id){
        if(marcaRepository.existsById(id)){
            marcaRepository.deleteById(id);
        }else{
            throw new RuntimeException("Marca não encontrada com ID: " + id);
        }
    }

}
