package tech.talci.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.talci.client.InventoryClient;
import tech.talci.orderservice.dto.OrderDto;
import tech.talci.orderservice.model.Order;
import tech.talci.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public String placeOrder(OrderDto orderDto) {
        boolean allProductsInStock = this.inventoryClient.isInStock(orderDto.getOrderLineItemsList());

        if (allProductsInStock) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .orderLineItems(orderDto.getOrderLineItemsList())
                    .build();

            this.orderRepository.save(order);

            log.debug("Order with order number: {} was created", order.getOrderNumber() );

            return String.format("Order with order number %s" +
                    " was successfully placed", order.getOrderNumber());
        } else {
            return "Order failed. One of the products is not in stock. Please try again";
        }
    }
}
