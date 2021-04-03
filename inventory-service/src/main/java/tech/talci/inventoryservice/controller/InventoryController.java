package tech.talci.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.inventoryservice.dto.OrderLineItems;
import tech.talci.inventoryservice.service.InventoryService;

import java.util.List;

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

    @PostMapping("/bulk-validation")
    public Boolean isInStock(@RequestBody List<OrderLineItems> orderLineItemsList) {
        return this.inventoryService.isInStock(orderLineItemsList);
    }

}
