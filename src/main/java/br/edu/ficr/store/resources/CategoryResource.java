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

import br.edu.ficr.store.entities.Category;
import br.edu.ficr.store.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/categories")
	@ApiOperation(value = "Add new category")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New category created"),
			@ApiResponse(code = 409, message = "Category already saved") })
	public ResponseEntity<Category> insert(@RequestBody Category category) {
		category = categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}

	@GetMapping("/categories")
	@ApiOperation(value = "Show all categories")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All categories listed") })
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/categories/{id}")
	@ApiOperation(value = "Find category by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category found by id"),
			@ApiResponse(code = 404, message = "Category not found") })
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok().body(category);
	}

	@PutMapping("/categories/{id}")
	@ApiOperation(value = "Update category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category updated"),
			@ApiResponse(code = 404, message = "Category not found") })
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
		category = categoryService.update(id, category);
		return ResponseEntity.ok().body(category);

	}

	@DeleteMapping("/categories/{id}")
	@ApiOperation(value = "Delete category")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Category deleted"),
			@ApiResponse(code = 404, message = "Category not found") })
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
