package com.project.ecom.services.customer.cart;

import com.project.ecom.dtos.AddProductInCartDto;
import com.project.ecom.entity.CartItemsEntity;
import com.project.ecom.entity.OrderEntity;
import com.project.ecom.entity.ProductEntity;
import com.project.ecom.entity.UserEntity;
import com.project.ecom.enums.OrderStatus;
import com.project.ecom.repositories.CartItemsRepository;
import com.project.ecom.repositories.OrderRepository;
import com.project.ecom.repositories.ProductRepository;
import com.project.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemsRepository cartItemsRepository;

    @Override
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        OrderEntity activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),
                OrderStatus.PENDING);
        Optional<CartItemsEntity> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<ProductEntity> product = productRepository.findById(addProductInCartDto.getProductId());
            Optional<UserEntity> user = userRepository.findById(addProductInCartDto.getUserId());

            if (product.isPresent() && user.isPresent()) {
                CartItemsEntity cart = CartItemsEntity.builder()
                        .product(product.get())
                        .price(product.get().getPrice())
                        .quantity(1L)
                        .user(user.get())
                        .order(activeOrder)
                        .build();
                cart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);
                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }
    }

}
