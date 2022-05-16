package br.edu.ficr.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ficr.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findByName(String name);

}