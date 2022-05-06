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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.services.SupplierService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class SupplierResource {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/suppliers")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a new supplier")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New supplier created"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 500, message = "Error adding supplier") })
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierService.addSupplier(supplier);
	}

	@PostMapping("/addProdSup")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Add Product to supplier")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New product add to supplier"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Product or supplier not found"),
			@ApiResponse(code = 500, message = "Error add product to supplier") })
	public Supplier addProdSup(@RequestParam Long productId, Long supplierId) {
		return supplierService.addProdSup(productId, supplierId);
	}

	@GetMapping("/suppliers")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listing all suppliers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All suppliers listed"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Suppliers not found"),
			@ApiResponse(code = 500, message = "Error listing all suppliers") })
	public List<Supplier> findAll() {
		return supplierService.findAll();
	}

	@GetMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Finding supplier by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Supplier found by id"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Supplier not found"),
			@ApiResponse(code = 500, message = "Error getting supplier") })
	public ResponseEntity<Supplier> findById(@PathVariable Long id) {
		return supplierService.findById(id);
	}

	@PutMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updating a supplier")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Supplier updated"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Supplier not found"),
			@ApiResponse(code = 500, message = "Error updating supplier") })
	public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
		return supplierService.updateSupplierById(supplier, id);
	}

	@DeleteMapping("/suppliers/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deleting a supplier")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Supplier deleted"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Supplier not found"),
			@ApiResponse(code = 500, message = "Error deleting product") })
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		return supplierService.deleteSupplierById(id);
	}

}
