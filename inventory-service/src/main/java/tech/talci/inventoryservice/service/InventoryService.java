package tech.talci.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.inventoryservice.exception.ResourceNotFoundException;
import tech.talci.inventoryservice.model.Inventory;
import tech.talci.inventoryservice.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public Boolean isInStock(String skuCode) {
        Inventory inventory = inventoryRepository
                .findBySkuCode(skuCode)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with skuCode " + skuCode
                        + " was not found"));

        return inventory.getStock() > 0;
    }
}
