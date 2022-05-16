package br.edu.ficr.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.repositories.ProductRepository;
import br.edu.ficr.store.repositories.SupplierRepository;
import br.edu.ficr.store.services.exceptions.AlreadyExistsException;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductRepository productRepository;

	public Supplier addProdSup(Long productId, Long supplierId) {
		Supplier supplier = supplierRepository.getById(supplierId);
		supplier.getProducts().add(productRepository.getById(productId));
		return supplierRepository.save(supplier);
	}

	public Supplier insert(Supplier obj) {
		
		List<Supplier> enititySaved = supplierRepository.findByName(obj.getName());
		if(!enititySaved.isEmpty()){
			throw new AlreadyExistsException("Supplier already saved: " + obj.getName());
		}
		return supplierRepository.save(obj);

	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public Supplier findById(Long id) {
		Optional<Supplier> obj = supplierRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
	}

	public Supplier update(Long id, Supplier obj) {
		try {
			Supplier entity = supplierRepository.getById(id);
			updateData(entity, obj);
			return supplierRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	public void delete(Long id) {
		try {
			supplierRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	private void updateData(Supplier entity, Supplier obj) {
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setEmail(obj.getEmail());
	}

}
