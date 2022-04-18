package br.edu.ficr.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.services.SupplierService;

@RestController
@RequestMapping("/api/v1")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("/suppliers")
	@ResponseStatus(HttpStatus.CREATED)
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierService.addSupplier(supplier);
	}
	
	@GetMapping("/suppliers")
	@ResponseStatus(HttpStatus.OK)
	public List<Supplier> findAll(){
		return supplierService.findAll();
	}
	
	@GetMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Supplier> findById(@PathVariable Long id){
		return supplierService.findById(id);
	}
	
	@PutMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Supplier> findById(@RequestBody Supplier supplier, @PathVariable Long id){
		return supplierService.updateSupplierById(supplier, id);
	}
	
	@DeleteMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteById(@PathVariable Long id){
		return supplierService.deleteSupplierById(id);
	}
	

}
