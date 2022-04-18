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

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public Category addCategory(@RequestBody Category cat) {
		return categoryService.addCategory(cat);
	}

	@GetMapping("/categories")
	@ResponseStatus(HttpStatus.OK)
	public List<Category> findAll() {
		return categoryService.findAll();
	}

	@GetMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PutMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Category> findById(@RequestBody Category category, @PathVariable Long id) {
		return categoryService.updateCategoryById(category, id);
	}

	@DeleteMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		return categoryService.deleteCategoryById(id);
	}

}
