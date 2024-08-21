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
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>("Cliente com id "+id+" nao encontrado.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Cliente>> findall(){
        try {
            List<Cliente> clientes = clienteService.findall();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            e.printStackTrace(); //linha para logar o erro no console dessa bomba
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            clienteService.deleteById(id);
            return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cliente com id "+id+" nao encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    //usado o responseentity como object porque da para retornar tanto msg de erro quanto a entity cliente
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") long id, @RequestBody Cliente updatedCliente) {
        try {
            Cliente cliente = clienteService.updateById(id, updatedCliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            // mensagem de erro personalizada lkkkk
            String errorMessage = e.getMessage();

            // mensagem de erro e o status de BAD_REQUEST
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

}


