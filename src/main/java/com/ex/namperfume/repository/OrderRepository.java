package com.ex.namperfume.repository;

import com.ex.namperfume.dto.response.OrderResponse;
import com.ex.namperfume.entity.Order;
import com.ex.namperfume.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByUser(User user);
}
