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
            return new ResponseEntity<>("Erro ao salvar funcionario", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Funcionario funcionario = funcionarioService.findById(id);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return new ResponseEntity<>("Funcionario com id "+id+" nao encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Funcionario>> findall(){
        try {
            List<Funcionario> funcionarios = funcionarioService.findall();
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            e.printStackTrace(); //linha para logar o erro no console dessa bomba
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            funcionarioService.deleteById(id);
            return new ResponseEntity<>("Funcionario deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Funcionario com id "+id+" nao encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    //usado o responseentity como object porque da para retornar tanto msg de erro quanto a entity funcionario
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") long id, @RequestBody Funcionario updatedFuncionario) {
        try {
            Funcionario funcionario = funcionarioService.updateById(id, updatedFuncionario);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e) {
            // mensagem de erro personalizada lkkkk
            String errorMessage = e.getMessage();

            // mensagem de erro e o status de BAD_REQUEST
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
