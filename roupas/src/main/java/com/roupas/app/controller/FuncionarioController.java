package com.roupas.app.controller;

import com.roupas.app.entity.Funcionario;
import com.roupas.app.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
        try {
            String msg = this.funcionarioService.save(funcionario);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar funcionario: "+ e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Funcionario funcionario = funcionarioService.findById(id);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoints de filtro
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Funcionario>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(funcionarioService.findByName(name));
    }

    @GetMapping("/registration/{registration}")
    public ResponseEntity<List<Funcionario>> findByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok(funcionarioService.findByRegistration(registration));
    }

    @GetMapping("/age/greater-than/{age}")
    public ResponseEntity<List<Funcionario>> findByAgeGreaterThanEqual(@PathVariable int age) {
        return ResponseEntity.ok(funcionarioService.findByAgeGreaterThanEqual(age));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll() {
        try {
            List<Funcionario> funcionarios = funcionarioService.findall();
            return ResponseEntity.ok(funcionarios);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            funcionarioService.deleteById(id);
            return new ResponseEntity<>("Funcionario deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //usado o responseentity como object porque da para retornar tanto msg de erro quanto a entity funcionario
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") long id, @RequestBody Funcionario updatedFuncionario) {
        try {
            Funcionario funcionario = funcionarioService.updateById(id, updatedFuncionario);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e) {
            // mensagem de erro e o status de BAD_REQUEST
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
