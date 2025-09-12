package com.project.ecom.repositories;

import com.project.ecom.entity.OrderEntity;
import com.project.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
