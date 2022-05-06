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
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a new category")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New category created"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 500, message = "Error adding category") })
	public Category addCategory(@RequestBody Category cat) {
		return categoryService.addCategory(cat);
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listing all categories")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All categories listed"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Categories not found"),
			@ApiResponse(code = 500, message = "Error listing all categories") })
	public List<Category> findAll() {
		return categoryService.findAll();
	}
	
	
	@GetMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Finding category by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category found by id"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Category not found"),
			@ApiResponse(code = 500, message = "Error getting category") })
	public ResponseEntity<Category> findById(@PathVariable Long id) {
			return categoryService.findById(id);
	}

	@PutMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updating a category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category updated"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Category not found"),
			@ApiResponse(code = 500, message = "Error updating category") })
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		return categoryService.updateCategoryById(category, id);
	}

	@DeleteMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deleting a category")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Category deleted"),
			@ApiResponse(code = 401, message = "Client not authenticated and not authorized to access resource"),
			@ApiResponse(code = 403, message = "Client do not have permission to access the resource"),
			@ApiResponse(code = 404, message = "Category not found"),
			@ApiResponse(code = 500, message = "Error deleting category") })
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		return categoryService.deleteCategoryById(id);
	}

}
