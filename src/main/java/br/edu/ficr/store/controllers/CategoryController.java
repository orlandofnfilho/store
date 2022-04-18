package br.edu.ficr.store.controllers;

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
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/categorys")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a new category")
	@ApiResponses( value = {
		@ApiResponse(code = 201, message = "New category created successful"),
		@ApiResponse(code = 500, message = "Error adding a category")
	})
	public Category addCategory(@RequestBody Category cat) {
		return categoryService.addCategory(cat);
	}

	@GetMapping("/categorys")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listing all categorys")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "All categories listed successfully"),
		@ApiResponse(code = 500, message = "Error listing all categories")
	})
	public List<Category> findAll() {
		return categoryService.findAll();
	}

	@GetMapping("/categorys/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Finding category by Id")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Category successfully found"),
		@ApiResponse(code = 404, message = "Category not found")
	})
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PutMapping("/categorys/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updating a category")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Category successfully updated"),
		@ApiResponse(code = 404, message = "Error updating the category")
	})
	public ResponseEntity<Category> findById(@RequestBody Category category, @PathVariable Long id) {
		return categoryService.updateCategoryById(category, id);
	}

	@DeleteMapping("/categorys/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deleting a category")
	@ApiResponses( value = {
		@ApiResponse(code = 204, message = "Category successfully deleted"),
		@ApiResponse(code = 404, message = "Category not found")
	})
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		return categoryService.deleteCategoryById(id);
	}

}
