package com.foodorder.order.service;

import com.foodorder.order.dto.OrderRequest;
import com.foodorder.order.exception.ResourceNotFoundException;
import com.foodorder.order.model.Order;
import com.foodorder.order.model.OrderStatus;
import com.foodorder.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    public Order placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setRestaurantId(request.getRestaurantId());
        order.setFoodItems(request.getFoodItems());
        order.setTotalAmount(request.getTotalAmount());
        Order savedOrder = orderRepository.save(order);
        kafkaProducerService.sendOrderNotification(
                "New order placed! OrderId: " + savedOrder.getId() +
                        " UserId: " + savedOrder.getUserId() +
                        " RestaurantId: " + savedOrder.getRestaurantId() +
                        " Items: " + savedOrder.getFoodItems() +
                        " Amount: " + savedOrder.getTotalAmount()
        );
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        kafkaProducerService.sendOrderNotification(
                "Order status updated! OrderId: " + order.getId() +
                        " New Status: " + status
        );
        return orderRepository.save(order);
    }

    public void cancelOrder(Long id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        kafkaProducerService.sendOrderNotification(
                "Order cancelled! OrderId: " + order.getId()
        );
    }
}