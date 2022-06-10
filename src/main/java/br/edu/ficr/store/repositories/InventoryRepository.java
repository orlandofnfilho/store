package br.edu.ficr.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ficr.store.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
