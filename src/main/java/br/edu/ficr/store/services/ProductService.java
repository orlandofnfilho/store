package br.edu.ficr.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.repositories.CategoryRepository;
import br.edu.ficr.store.repositories.ProductRepository;
import br.edu.ficr.store.repositories.SupplierRepository;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	public Product addProduct(@RequestBody Product product) {
		if (product.getCategory() != null) {
			categoryRepository.save(product.getCategory());
		}
		if (!product.getSuppliers().isEmpty()) {
			supplierRepository.saveAll(product.getSuppliers());
		}
		return productRepository.save(product);

	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public ResponseEntity<Product> findById(Long id) {
		return productRepository.findById(id).map(product -> ResponseEntity.ok().body(product))
				.orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Product> updateProductById(Product product, Long id) {
		return productRepository.findById(id).map(productToUpdate -> {
//			setStock(productToUpdate);
			Product updated = productRepository.save(productToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Object> deleteProductById(Long id) {
		return productRepository.findById(id).map(productToDelete -> {
			productRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}
}
