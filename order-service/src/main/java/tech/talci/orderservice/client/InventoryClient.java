package tech.talci.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.talci.orderservice.model.OrderLineItems;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/inventory/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode);

    @GetMapping("/api/inventory/bulk-validation")
    Boolean isInStock(@RequestBody List<OrderLineItems> orderLineItemsList);
}
