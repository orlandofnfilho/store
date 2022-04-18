package br.edu.ficr.store.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.entities.StockStatus;
import br.edu.ficr.store.repositories.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(@RequestBody Product product) {
		product.setUpdatedAt(Instant.now());
		setStock(product);
		return productRepository.save(product);

	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public ResponseEntity<Product> findById(Long id) {
		return productRepository.findById(id).map(product -> ResponseEntity.ok().body(product))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Product> updateProductById(Product product, Long id) {
		return productRepository.findById(id).map(productToUpdate -> {
			setStock(productToUpdate);
			Product updated = productRepository.save(productToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> deleteProductById(Long id) {
		return productRepository.findById(id).map(productToDelete -> {
			productRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	public static void setStock(Product product) {
		if (product.getQuantity() > 0) {
			product.setStatus(StockStatus.IN_STOCK);
		}
		if (product.getQuantity() <= 0 || product.getQuantity()==null) {
			product.setStatus(StockStatus.OUT_OF_STOCK);
		}
	}
}
