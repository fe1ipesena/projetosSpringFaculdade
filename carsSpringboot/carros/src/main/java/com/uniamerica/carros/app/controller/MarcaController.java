package com.uniamerica.carros.app.controller;

import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Marca marca) {

		try {
			String mensagem = this.marcaService.save(marca);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao salvar", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Marca> findById(@PathVariable("id") long id){
		try {
			Marca entity = marcaService.findById(id);
			return ResponseEntity.ok(entity);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Marca>> findAll() {
		try {
			List<Marca> marcas = marcaService.findAll();
			return ResponseEntity.ok(marcas);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<Marca> updateById(@PathVariable("id") long id, @RequestBody Marca updatedMarca) {
		try {
			Marca updated = marcaService.updateById(id, updatedMarca);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e){
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public void deleteByID(@PathVariable("id") long id){
			marcaService.deleteById(id);

	}

}
