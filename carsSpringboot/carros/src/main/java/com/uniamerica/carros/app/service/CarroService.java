package com.uniamerica.carros.app.service;

import com.uniamerica.carros.app.entity.Carro;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.repository.CarroRepository;
import com.uniamerica.carros.app.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public String save(Carro carro) {
        if (carro.getMarca() != null) {
            Marca marca = marcaRepository.findById(carro.getMarca().getId()).orElse(null);
            if (marca == null) {
                return "Marca não encontrada";
            }
            carro.setMarca(marca);
            // Adiciona o carro à lista de carros da marca
            marca.getCarros().add(carro);

            // Atualiza a marca
            marcaRepository.save(marca);
        }

        carroRepository.save(carro);
        return "Carro salvo com sucesso";
    }

    public Carro findById(long id) {
        return carroRepository.findById(id).get();
    }

    public List<Carro> findAll(){
        return carroRepository.findAll();
    }

    public Carro updateById(long id, Carro updatedCarro){
        Optional<Carro> op = carroRepository.findById(id);
        if(op.isPresent()){
            Carro existingCarro = op.get();

            if(updatedCarro.getNome() != null){
                existingCarro.setNome(updatedCarro.getNome());
            }
            return carroRepository.save(existingCarro);
        }
        throw new RuntimeException("Marca com ID " + id + " não encontrada");
    }

    public void deleteById(long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Carro não encontrado com ID: " + id);
        }
    }

}
