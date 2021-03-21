package tech.talci.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.talci.orderservice.dto.OrderDto;
import tech.talci.orderservice.model.Order;
import tech.talci.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public String placeOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderDto.getOrderLineItems())
                .build();

        this.orderRepository.save(order);

        log.debug("Order with order number: {} was created", order.getOrderNumber() );

        return String.format("Order with order number %s" +
                " was successfully placed", order.getOrderNumber());
    }
}
