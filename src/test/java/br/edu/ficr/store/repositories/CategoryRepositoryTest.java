package br.edu.ficr.store.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.ficr.store.entities.Category;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	private Category createCategory() {
		return Category.builder().name("Tech").build();
	}

	@Test
	@DisplayName("Should save a category when sucessful")
	void shouldSaveACategoryWhenSucessful() {

		Category categoryToBeSave = createCategory();
		Category categorySaved = this.categoryRepository.save(categoryToBeSave);
		Assertions.assertNotNull(categorySaved);
		Assertions.assertEquals(categorySaved.getName(), categoryToBeSave.getName());
	}

	@Test
	@DisplayName("Should find a category by name")
	void shouldFindCategoryByName() {

		Category categoryCreated = createCategory();
		categoryRepository.save(categoryCreated);
		Optional<Category> cat1 = categoryRepository.findByName(categoryCreated.getName());
		Assertions.assertFalse(cat1.isEmpty());
	}

}
