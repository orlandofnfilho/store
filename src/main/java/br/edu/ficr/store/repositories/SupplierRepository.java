package br.edu.ficr.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ficr.store.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
