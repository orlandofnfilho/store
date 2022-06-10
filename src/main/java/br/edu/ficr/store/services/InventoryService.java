package br.edu.ficr.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ficr.store.entities.Inventory;
import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.entities.enums.StockStatus;
import br.edu.ficr.store.repositories.InventoryRepository;
import br.edu.ficr.store.repositories.ProductRepository;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;
import br.edu.ficr.store.services.exceptions.InventoryException;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public Inventory findById(Long id) {
		Optional<Inventory> obj = inventoryRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
	}

	public List<Inventory> findAll() {
		return inventoryRepository.findAll();
	}

	public Inventory entry(Product obj, Integer qt) {
		Optional<Product> product = productRepository.findById(obj.getId());
		if (product.isEmpty()) {
			throw new EntityNotFoundException("Id not found: " + obj.getId());
		}

		Inventory inventory = product.get().getInventory();

		int result = inventory.getUnitQt() + qt;

		inventory.setUnitQt(result);
		if (result > 0) {
			inventory.setStatus(StockStatus.IN_STOCK);
		}

		return inventoryRepository.save(inventory);

	}

	public Inventory output(Product obj, Integer qt) {

		Optional<Product> product = productRepository.findById(obj.getId());
		if (product.isEmpty()) {
			throw new EntityNotFoundException("Id not found: " + obj.getId());
		}

		Inventory inventory = product.get().getInventory();
		if (qt > inventory.getUnitQt()) {
			throw new InventoryException("Only " + inventory.getUnitQt() + " itens left in stock");
		}

		int result = inventory.getUnitQt() - qt;

		if (result == 0) {
			inventory.setStatus(StockStatus.OUT_OF_STOCK);
		}

		inventory.setUnitQt(result);

		return inventoryRepository.save(inventory);

	}

}
