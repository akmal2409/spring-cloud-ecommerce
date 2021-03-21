package tech.talci.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.talci.inventoryservice.service.InventoryService;

@RestController
@RequestMapping(InventoryController.BASE_URL)
@RequiredArgsConstructor
public class InventoryController {
    public static final String BASE_URL = "/api/inventory";
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public Boolean isInStock(@PathVariable String skuCode) {
        return this.inventoryService.isInStock(skuCode);
    }
}
