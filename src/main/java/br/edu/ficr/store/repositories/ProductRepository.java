package br.edu.ficr.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ficr.store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}