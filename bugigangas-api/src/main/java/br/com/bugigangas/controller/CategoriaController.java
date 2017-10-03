package br.com.bugigangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bugigangas.model.Categoria;
import br.com.bugigangas.repository.CategoriaRepository;
import br.com.bugigangas.requests.CategoriaRequest;


@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository catRepository;
	
	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public List<Categoria> listaCategorias(){
		return catRepository.findAll();
	}
	
	@RequestMapping(value = "/categorias", method = RequestMethod.POST)
	public ResponseEntity<Categoria> inserirCategoria(@RequestBody CategoriaRequest catReq){
		Categoria categoria = new Categoria ();
		
		categoria.setDescricao(catReq.getDescricao());
		categoria.setNome(catReq.getNome());
		
		catRepository.save(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/categoria/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Categoria> alterarCategoria(@PathVariable("id") Integer id, @RequestBody CategoriaRequest catReq ){
		Categoria categoria = new Categoria();
		
		categoria.setDescricao(catReq.getDescricao());
		categoria.setNome(catReq.getNome());
		
		catRepository.save(categoria);
		return new ResponseEntity<Categoria>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/categoria/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		catRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
