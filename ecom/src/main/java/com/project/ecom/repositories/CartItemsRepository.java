package com.project.ecom.repositories;

import com.project.ecom.entity.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItemsEntity, Long> {

    Optional<CartItemsEntity> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);
}
