package tech.talci.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.orderservice.model.OrderLineItems;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
}
