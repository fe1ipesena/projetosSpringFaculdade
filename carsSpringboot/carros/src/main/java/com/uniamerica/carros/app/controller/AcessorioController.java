package com.uniamerica.carros.app.controller;

import com.uniamerica.carros.app.entity.Acessorio;
import com.uniamerica.carros.app.service.AcessorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {

    @Autowired
    private AcessorioService acessorioService;

    @PostMapping("/save")
    public ResponseEntity<Acessorio> save(@RequestBody Acessorio acessorio) {
        try {
            Acessorio savedAcessorio = acessorioService.save(acessorio);
            return new ResponseEntity<>(savedAcessorio, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Acessorio> findById(@PathVariable("id") long id) {
        try {
            Acessorio entity = acessorioService.findById(id);
            return ResponseEntity.ok(entity);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Acessorio>> findAll() {
        List<Acessorio> acessorios = (List<Acessorio>) acessorioService.findAll();
        return new ResponseEntity<>(acessorios, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {
            acessorioService.deleteById(id);
            return new ResponseEntity<>("Acessório deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar o acessório", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Acessorio> updateById(@PathVariable("id") long id, @RequestBody Acessorio updatedAcessorio) {
        try {
            Acessorio updated = acessorioService.updateById(id, updatedAcessorio);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
