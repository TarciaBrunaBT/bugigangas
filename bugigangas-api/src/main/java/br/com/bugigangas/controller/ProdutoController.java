package br.com.bugigangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bugigangas.model.Categoria;
import br.com.bugigangas.model.Produto;
import br.com.bugigangas.repository.ProdutoRepository;
import br.com.bugigangas.requests.ProdutoRequest;


@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public List<Produto> listaProdutos() {
		return repository.findAll();
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public ResponseEntity<Produto> inserirProduto(@RequestBody ProdutoRequest prodReq) {
		Produto produto = new Produto();
		produto.setDescricao(prodReq.getDescricao());
		produto.setNome(prodReq.getNome());
		produto.setPreco(produto.getPreco());

		Categoria cat = new Categoria();
		cat.setId(prodReq.getCategoria());
		produto.setCategoria(cat);

		repository.save(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/produtos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Produto> alterarProduto(@PathVariable("id") Integer id, @RequestBody ProdutoRequest prodReq) {
		Produto produto = repository.getOne(id);
		produto.setDescricao(prodReq.getDescricao());
		produto.setNome(prodReq.getNome());
		produto.setPreco(produto.getPreco());

		Categoria cat = new Categoria();
		cat.setId(prodReq.getCategoria());
		produto.setCategoria(cat);

		repository.save(produto);
		return new ResponseEntity<Produto>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produtos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
