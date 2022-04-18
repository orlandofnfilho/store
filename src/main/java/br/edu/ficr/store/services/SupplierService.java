package br.edu.ficr.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.repositories.SupplierRepository;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierRepository.save(supplier);

	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public ResponseEntity<Supplier> findById(Long id) {
		return supplierRepository.findById(id).map(supplier -> ResponseEntity.ok().body(supplier))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Supplier> updateSupplierById(Supplier supplier, Long id) {
		return supplierRepository.findById(id).map(supplierToUpdate -> {
			Supplier updated = supplierRepository.save(supplierToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> deleteSupplierById(Long id){
		return supplierRepository.findById(id)
				.map(supplierToDelete ->{
					supplierRepository.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
