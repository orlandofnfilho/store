package br.edu.ficr.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.repositories.ProductRepository;
import br.edu.ficr.store.repositories.SupplierRepository;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductRepository productRepository;

	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierRepository.save(supplier);

	}

	public Supplier addProdSup(@RequestParam Long productId, Long supplierId) {
		Supplier supplier = supplierRepository.getById(supplierId);
		supplier.getProducts().add(productRepository.getById(productId));
		return supplierRepository.save(supplier);

	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public ResponseEntity<Supplier> findById(Long id) {
		return supplierRepository.findById(id).map(supplier -> ResponseEntity.ok().body(supplier))
				.orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Supplier> updateSupplierById(Supplier supplier, Long id) {
		return supplierRepository.findById(id).map(supplierToUpdate -> {
			Supplier updated = supplierRepository.save(supplierToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Object> deleteSupplierById(Long id) {
		return supplierRepository.findById(id).map(supplierToDelete -> {
			supplierRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}
}
