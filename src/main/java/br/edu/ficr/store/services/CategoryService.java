package br.edu.ficr.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ficr.store.entities.Category;
import br.edu.ficr.store.repositories.CategoryRepository;
import br.edu.ficr.store.services.exceptions.AlreadyExistsException;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category insert(Category obj) {
		Optional<Category> enititySaved = categoryRepository.findByName(obj.getName());
		if (!enititySaved.isEmpty()) {
			throw new AlreadyExistsException("Category already saved: " + obj.getName());
		}
		return categoryRepository.save(obj);

	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
	}

	public Category update(Long id, Category obj) {
		try {
			Category entity = categoryRepository.getById(id);
			updateData(entity, obj);
			return categoryRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found: " + id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
