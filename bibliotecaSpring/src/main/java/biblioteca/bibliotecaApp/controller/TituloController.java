package biblioteca.bibliotecaApp.controller;

import biblioteca.bibliotecaApp.services.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.bibliotecaApp.entity.Titulo;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class TituloController {

    @Autowired
    private TituloService tituloService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Titulo titulo){
        try {
            String msg = this.tituloService.save(titulo);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Erro ao salvar titulo", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Titulo> findById(@PathVariable int id){
        try {
            Titulo titulo = this.tituloService.findById(id);
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Titulo>> findall() {
        try {
            List<Titulo> titulos = tituloService.findAll();
            return ResponseEntity.ok(titulos);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para logar o erro no console
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteById(@PathVariable("id")long id){
        try {
            tituloService.deleteById(id);
            return new ResponseEntity<>("Livro deletado com sucesso.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<Titulo> updateById(@PathVariable("id")long id, @RequestBody Titulo updatedTitulo){
        try {
            Titulo titulo = tituloService.updateById(id, updatedTitulo);
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    }




