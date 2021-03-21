package tech.talci.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
