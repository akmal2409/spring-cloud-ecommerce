package tech.talci.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.inventoryservice.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
}
