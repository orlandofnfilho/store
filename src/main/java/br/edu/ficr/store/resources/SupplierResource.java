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

import br.edu.ficr.store.entities.Supplier;
import br.edu.ficr.store.services.SupplierService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/suppliers")
public class SupplierResource {

	@Autowired
	private SupplierService supplierService;

	@PostMapping
	@ApiOperation(value = "Add new supplier")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New supplier created"),
			@ApiResponse(code = 409, message = "Supplier already saved") })
	public ResponseEntity<Supplier> insert(@RequestBody Supplier supplier) {
		supplier = supplierService.insert(supplier);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(supplier.getId())
				.toUri();
		return ResponseEntity.created(uri).body(supplier);
	}

	@GetMapping
	@ApiOperation(value = "Show all suppliers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All suppliers listed") })
	public ResponseEntity<List<Supplier>> findAll() {
		List<Supplier> list = supplierService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find supplier by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Supplier found by id"),
			@ApiResponse(code = 404, message = "Supplier not found") })
	public ResponseEntity<Supplier> findById(@PathVariable Long id) {
		Supplier supplier = supplierService.findById(id);
		return ResponseEntity.ok().body(supplier);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update supplier")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Supplier updated"),
			@ApiResponse(code = 404, message = "Supplier not found") })
	public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier supplier) {
		supplier = supplierService.update(id, supplier);
		return ResponseEntity.ok().body(supplier);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete supplier")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Supplier deleted"),
			@ApiResponse(code = 404, message = "Supplier not found") })
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		supplierService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
