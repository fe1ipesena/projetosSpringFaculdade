package com.roupas.app.controller;

import com.roupas.app.entity.Produto;
import com.roupas.app.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Produto produto){
        try {
            String msg = this.produtoService.save(produto);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar produto: "+ e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try {
            Produto produto = produtoService.findById(id);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoints de filtro
    @GetMapping("/product/{product}")
    public ResponseEntity<List<Produto>> findByProduct(@PathVariable String product) {
        return ResponseEntity.ok(produtoService.findByProduct(product));
    }

    @GetMapping("/value/between")
    public ResponseEntity<List<Produto>> findByValueBetween(@RequestParam BigDecimal minValue, @RequestParam BigDecimal maxValue) {
        return ResponseEntity.ok(produtoService.findByValueBetween(minValue, maxValue));
    }

    @GetMapping("/value/greater-than/{value}")
    public ResponseEntity<List<Produto>> findByValueGreaterThan(@PathVariable BigDecimal value) {
        return ResponseEntity.ok(produtoService.findByValueGreaterThan(value));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll() {
        try {
            List<Produto> funcionarios = produtoService.findall();
            return ResponseEntity.ok(funcionarios);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            produtoService.deleteById(id);
            return new ResponseEntity<>("Produto deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //usado o responseentity como object porque da para retornar tanto msg de erro quanto a entity funcionario
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") long id, @RequestBody Produto updatedProduto) {
        try {
            Produto produto = produtoService.updateById(id, updatedProduto);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (Exception e) {
            // mensagem de erro e o status de BAD_REQUEST
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
