package com.roupas.app.controller;

import com.roupas.app.entity.Cliente;
import com.roupas.app.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Cliente cliente){
        try {
            String msg = this.clienteService.save(cliente);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar cliente "+ e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoints de filtro
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cliente>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(clienteService.findByName(name));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<Cliente>> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.findByCpf(cpf));
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Cliente>> findByAge(@PathVariable int age) {
        return ResponseEntity.ok(clienteService.findByAge(age));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findall(){
        try {
            List<Cliente> clientes = clienteService.findall();
            return ResponseEntity.ok(clientes);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            clienteService.deleteById(id);
            return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //usado o responseentity como object porque da para retornar tanto msg de erro quanto a entity cliente
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") long id, @RequestBody Cliente updatedCliente) {
        try {
            Cliente cliente = clienteService.updateById(id, updatedCliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            // mensagem de erro e o status de BAD_REQUEST
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}


