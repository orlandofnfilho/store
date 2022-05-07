package br.edu.ficr.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ficr.store.entities.Category;
import br.edu.ficr.store.repositories.CategoryRepository;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category addCategory(Category category) {
		return categoryRepository.save(category);

	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public ResponseEntity<Category> findById(Long id) {
		return categoryRepository.findById(id).map(category -> ResponseEntity.ok().body(category))
				.orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Category> updateCategoryById(Category category, Long id) {
		return categoryRepository.findById(id).map(categoryToUpdate -> {
			categoryToUpdate.setName(category.getName());
			Category updated = categoryRepository.save(categoryToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}

	public ResponseEntity<Object> deleteCategoryById(Long id) {
		return categoryRepository.findById(id).map(categoryToDelete -> {
			categoryRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
	}
}
