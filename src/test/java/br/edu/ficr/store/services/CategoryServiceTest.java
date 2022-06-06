package br.edu.ficr.store.services;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.edu.ficr.store.entities.Category;
import br.edu.ficr.store.repositories.CategoryRepository;
import br.edu.ficr.store.services.exceptions.AlreadyExistsException;
import br.edu.ficr.store.services.exceptions.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

	private static final Long ID = 1L;
	private static final String NAME = "Tech";
	private static final Integer INDEX = 0;
	private static final String ID_NAO_ENCONTRADO = "Id not found: " + ID;

	private Category category;
	private Optional<Category> optionalCategory;

	@InjectMocks
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setup() {
		startCategory();

	}

	@Test
	public void shouldFindACategoryById() {
		when(categoryRepository.findById(Mockito.anyLong())).thenReturn(optionalCategory);
		Category response = categoryService.findById(ID);

		Assertions.assertNotNull(response);

		Assertions.assertEquals(Category.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(NAME, response.getName());
		Assertions.assertEquals(null, response.getProducts());

	}

	@Test
	public void shouldListCategoriesSaved() {

		when(categoryRepository.findAll()).thenReturn(List.of(category));

		List<Category> response = categoryService.findAll();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(Category.class, response.get(INDEX).getClass());

	}

	@Test
	public void shouldThrowEntityNotFound() {
		when(categoryRepository.findById(Mockito.anyLong()))
				.thenThrow(new EntityNotFoundException("Id not found: " + ID));

		try {
			categoryService.findById(ID);

		} catch (EntityNotFoundException e) {
			Assertions.assertEquals(EntityNotFoundException.class, e.getClass());
			Assertions.assertEquals(ID_NAO_ENCONTRADO, e.getMessage());

		}

	}

	@Test
	public void shouldThrowAlreadyExistsException() {
		when(categoryRepository.findByName(Mockito.anyString())).thenReturn(optionalCategory);

		try {
			optionalCategory.get().setId(2L);
			categoryService.insert(category);

		} catch (AlreadyExistsException e) {
			Assertions.assertEquals(AlreadyExistsException.class, e.getClass());
			Assertions.assertEquals("Category already saved: " + NAME, e.getMessage());
		}
	}

	@Test
	public void shouldCreateACategory() {
		when(categoryRepository.save(Mockito.any())).thenReturn(category);

		Category response = categoryService.insert(category);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(Category.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(NAME, response.getName());
	}

	private void startCategory() {
		category = new Category(ID, NAME, null);
		optionalCategory = Optional.of(new Category(ID, NAME, null));
	}

}
