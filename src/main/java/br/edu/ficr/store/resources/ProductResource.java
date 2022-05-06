package br.edu.ficr.store.resources;

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

import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductResource {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a new product")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New product created"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 500, message = "Error adding product") })
	public Product addProduct(@RequestBody Product product) {
		log.info("Added a new product: [{}]", product);
		return productService.addProduct(product);
	}

	@GetMapping("/products")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listing all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All prodcuts listed"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Products not found"),
			@ApiResponse(code = 500, message = "Error listing all products") })
	public List<Product> findAll() {
		log.info("Show all products");
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Finding product by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product found by id"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Product not found"),
			@ApiResponse(code = 500, message = "Error getting product") })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		log.info("Find product by Id: [{}]", id);
		return productService.findById(id);
	}

	@PutMapping("/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updating a product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product updated"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Product not found"),
			@ApiResponse(code = 500, message = "Error updating product") })
	public ResponseEntity<Product> findById(@RequestBody Product product, @PathVariable Long id) {
		log.info("Updateding product: [{}] with infos: [{}]", id, product);
		return productService.updateProductById(product, id);
	}

	@DeleteMapping("/products/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deleting a product")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Product deleted"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Product not found"),
			@ApiResponse(code = 500, message = "Error deleting product") })
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		log.info("Deleting product: [{}]", id);
		return productService.deleteProductById(id);
	}

}
