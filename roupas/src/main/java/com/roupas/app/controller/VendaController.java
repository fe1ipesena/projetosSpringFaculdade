package com.roupas.app.controller;

import com.roupas.app.entity.Produto;
import com.roupas.app.entity.Venda;
import com.roupas.app.services.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Venda venda) {
        try {
            // Salvar a venda
            Venda vendaSalva = vendaService.save(venda);
            return new ResponseEntity<>(vendaSalva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar venda: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Venda venda = vendaService.findById(id);
            return ResponseEntity.ok(venda);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll() {
        try {
            List<Venda> vendas = vendaService.findall();
            return ResponseEntity.ok(vendas);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            vendaService.deleteById(id);
            return new ResponseEntity<>("Venda deletada com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") long id, @RequestBody Venda updatedVenda) {
        try {
            Venda updated = vendaService.updateById(id, updatedVenda);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
