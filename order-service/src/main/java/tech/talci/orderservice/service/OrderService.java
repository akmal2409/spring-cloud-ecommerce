package tech.talci.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import tech.talci.orderservice.client.InventoryClient;
import tech.talci.orderservice.dto.OrderDto;
import tech.talci.orderservice.model.Order;
import tech.talci.orderservice.repository.OrderRepository;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;


    public String placeOrder(OrderDto orderDto) {
        if (orderDto == null) {
            throw new RuntimeException("Order is not present");
        }

        Resilience4JCircuitBreaker circuitBreaker = this.circuitBreakerFactory
                .create("inventory");

        Supplier<Boolean> stockSupplier = () -> orderDto.getOrderLineItemsList()
                .stream()
                .allMatch(item -> this.inventoryClient.isInStock(item.getSkuCode()));

        circuitBreaker.run(stockSupplier, throwable -> handleErrorCase());

        boolean allProductsInStock = stockSupplier.get();

        if (allProductsInStock) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .orderLineItems(orderDto.getOrderLineItemsList())
                    .build();

            this.orderRepository.save(order);

            log.info("Sending Order Details to Notification Service");
            streamBridge.send("notificationEventSupplier-out-0", order.getId());


            log.debug("Order with order number: {} was created", order.getOrderNumber() );

            return String.format("Order with order number %s" +
                    " was successfully placed", order.getOrderNumber());
        } else {
            return "Order failed. One of the products is not in stock. Please try again";
        }
    }

    private Boolean handleErrorCase() {
        return Boolean.FALSE;
    }
}
