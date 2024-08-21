package biblioteca.bibliotecaApp.controller;

import biblioteca.bibliotecaApp.entity.Venda;
import biblioteca.bibliotecaApp.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Venda venda){
        try {
            String msg = this.vendaService.save(venda);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Erro ao salvar venda", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Venda> findById(@PathVariable int id){
        try {
            Venda venda = this.vendaService.findById(id);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Venda>> findall() {
        try {
            List<Venda> vendas = vendaService.findAll();
            return ResponseEntity.ok(vendas);
        } catch (Exception e) {
            e.printStackTrace(); //linha para logar o erro no console dessa bomba
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            vendaService.deleteById(id);
            return new ResponseEntity<>("venda deletada com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Venda> updateById(@PathVariable("id")long id, @RequestBody Venda updatedVenda){
        try {
            Venda venda = vendaService.updateById(id, updatedVenda);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
