package biblioteca.bibliotecaApp.controller;


import biblioteca.bibliotecaApp.entity.Cliente;
import biblioteca.bibliotecaApp.entity.Funcionario;
import biblioteca.bibliotecaApp.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
        try {
            String msg = this.funcionarioService.save(funcionario);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Erro ao salvar funcionario", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable int id){
        try {
            Funcionario funcionario = this.funcionarioService.findById(id);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Funcionario>> findall() {
        try {
            List<Funcionario> funcionarios = funcionarioService.findAll();
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
            return new ResponseEntity<>("funcionario deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Funcionario> updateById(@PathVariable("id")long id, @RequestBody Funcionario updatedFuncionario){
        try {
            Funcionario funcionario = funcionarioService.updateById(id, updatedFuncionario);
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
