package br.edu.ficr.store.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ficr.store.entities.Inventory;
import br.edu.ficr.store.entities.Product;
import br.edu.ficr.store.services.InventoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class InventoryResource {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/inventories")
	@ApiOperation(value = "Show all inventories")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All inventories listed") })
	public ResponseEntity<List<Inventory>> findAll() {
		List<Inventory> list = inventoryService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/inventories/{id}")
	@ApiOperation(value = "Find category by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Inventory found by id"),
			@ApiResponse(code = 404, message = "Inventory not found") })
	public ResponseEntity<Inventory> findById(@PathVariable Long id) {
		Inventory inventory = inventoryService.findById(id);
		return ResponseEntity.ok().body(inventory);
	}

	@PostMapping("/inventories/entry")
	@ApiOperation(value = "Entry products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Entry products") })
	public ResponseEntity<Inventory> entry(@RequestBody Product obj, @RequestParam Integer qt) {
		Inventory inventory = inventoryService.entry(obj, qt);
		return ResponseEntity.ok().body(inventory);
	}

	@PostMapping("/inventories/output")
	@ApiOperation(value = "Output products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Output products") })
	public ResponseEntity<Inventory> output(@RequestBody Product obj, @RequestParam Integer qt) {
		Inventory inventory = inventoryService.output(obj, qt);
		return ResponseEntity.ok().body(inventory);
	}

}
