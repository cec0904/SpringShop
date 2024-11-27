package org.example.springshop;

import org.example.springshop.domain.*;
import org.example.springshop.service.ItemService;
import org.example.springshop.service.OrderService;
import org.example.springshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class OrderTestRunner implements CommandLineRunner {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    @Override
    public void run(String... args) throws Exception {
        // Order 생성
        User user = new User();
        user.setEmail("user@example.com");
        user.setNickname("User1");
        user.setPassword("password123");
        userService.saveUser(user);

        Item item = new Item();
        item.setName("Product A");
        item.setPrice(1000);
        item.setStock(10);
        itemService.saveItem(item);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(2);
        orderItem.setOrderPrice(item.getPrice() * orderItem.getCount());

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDERED)
                .build();


        orderItem.setOrder(order);
        order.getOrderItems().add(orderItem);

        // Order 저장
        orderService.saveOrder(order);

        // Order 조회
        Order foundOrder = orderService.findOrder(order.getId());
        System.out.println("Order ID: " + foundOrder.getId());
        System.out.println("Total Quantity: " + foundOrder.getTotalQuantity());
        System.out.println("First Item Name: " + foundOrder.getFirstItemName());
    }
}

