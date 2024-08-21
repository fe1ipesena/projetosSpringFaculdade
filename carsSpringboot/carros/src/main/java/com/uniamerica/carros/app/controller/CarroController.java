package com.uniamerica.carros.app.controller;

import com.uniamerica.carros.app.entity.Carro;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.service.CarroService;
import com.uniamerica.carros.app.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")

public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private MarcaService marcaService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Carro carro) {
        try {
            String mensagem = this.carroService.save(carro);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Carro> findById(@PathVariable("id") long id){
        try {
            Carro entity = carroService.findById(id);
            return ResponseEntity.ok(entity);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Carro>> findAll() {
        List<Carro> carros = carroService.findAll();
        return ResponseEntity.ok(carros);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Carro> updateById(@PathVariable("id") long id, @RequestBody Carro updatedCarro) {
        try {
            Carro updated = carroService.updateById(id, updatedCarro);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteByID(@PathVariable("id") long id){
        carroService.deleteById(id);
    }

}
