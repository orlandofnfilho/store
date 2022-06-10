package br.edu.ficr.store.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ficr.store.entities.Inventory;
import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.entities.enums.StockStatus;
import br.edu.ficr.store.repositories.CategoryRepository;
import br.edu.ficr.store.repositories.ProductRepository;
import br.edu.ficr.store.repositories.SupplierRepository;
import br.edu.ficr.store.services.exceptions.AlreadyExistsException;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	public Product insert(Product obj) {
		if (obj.getCategory() != null) {
			categoryRepository.save(obj.getCategory());
		}
		if (!obj.getSuppliers().isEmpty()) {
			supplierRepository.saveAll(obj.getSuppliers());
		}
		Optional<Product> enititySaved = productRepository.findByName(obj.getName());
		if (!enititySaved.isEmpty()) {
			throw new AlreadyExistsException("Product already saved: " + obj.getName());
		}

		Inventory inventory = new Inventory(null, 0, Instant.now(), StockStatus.OUT_OF_STOCK, obj);

		obj.setInventory(inventory);

		return productRepository.save(obj);

	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
	}

	public Product update(Long id, Product obj) {
		try {
			Product entity = productRepository.getById(id);
			updateData(entity, obj);
			return productRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	public Product addSupplier(Product obj) {
		try {
			Product entity = productRepository.getById(obj.getId());
			entity.getSuppliers().addAll(obj.getSuppliers());
			return productRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found: " + obj.getId());
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());
		entity.setBrand(obj.getBrand());
		entity.setSku(obj.getSku());
		entity.setWeight(obj.getWeight());
		entity.setDescription(obj.getDescription());
		entity.setCategory(obj.getCategory());
	}

}
