package br.edu.ficr.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ficr.store.entities.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
