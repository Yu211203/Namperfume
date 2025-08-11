package com.ex.namperfume.repository;

import com.ex.namperfume.entity.Order;
import com.ex.namperfume.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByOrder(Order order);
}
