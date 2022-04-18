package br.edu.ficr.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ficr.store.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
