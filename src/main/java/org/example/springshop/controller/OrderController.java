package org.example.springshop.controller;

import org.example.springshop.domain.Order;
import org.example.springshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orderList";
    }

    @PostMapping("/order")
    public String createOrder(Order order) {
        orderService.createOrder(order);
        return "redirect:/orderList";
    }
}
