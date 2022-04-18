package br.edu.ficr.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ficr.store.entities.Category;
import br.edu.ficr.store.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category addCategory(@RequestBody Category category) {
		return categoryRepository.save(category);

	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public ResponseEntity<Category> findById(Long id) {
		return categoryRepository.findById(id).map(category -> ResponseEntity.ok().body(category))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Category> updateCategoryById(Category category, Long id) {
		return categoryRepository.findById(id).map(categoryToUpdate -> {
			Category updated = categoryRepository.save(categoryToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> deleteCategoryById(Long id) {
		return categoryRepository.findById(id).map(categoryToDelete -> {
			categoryRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
