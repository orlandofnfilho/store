package br.edu.ficr.store.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.repositories.InventoryRepository;
import br.edu.ficr.store.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryRepository repository;

	@PostMapping("/products")
	@ApiOperation(value = "Add new product")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New category created"),
			@ApiResponse(code = 409, message = "Category already saved") })
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@GetMapping("/products")
	@ApiOperation(value = "Show all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Show all products") })
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = productService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/products/{id}")
	@ApiOperation(value = "Find product by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product found by id"),
			@ApiResponse(code = 404, message = "Product not found") })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@PutMapping("/products/{id}")
	@ApiOperation(value = "Update product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product updated"),
			@ApiResponse(code = 404, message = "Product not found") })
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		product = productService.update(id, product);
		return ResponseEntity.ok().body(product);

	}

	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "Delete product")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Product deleted"),
			@ApiResponse(code = 404, message = "Product not found") })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/products/")
	@ApiOperation(value = "Add suppliers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product updated"),
			@ApiResponse(code = 404, message = "Product not found") })
	public ResponseEntity<Product> addSupplier(@RequestBody Product product) {
		product = productService.addSupplier(product);
		return ResponseEntity.ok().body(product);

	}
}
