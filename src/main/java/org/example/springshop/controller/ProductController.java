package org.example.springshop.controller;

import org.example.springshop.domain.Product;
import org.example.springshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/itemList")
    public String itemList(Model model) {
        model.addAttribute("items", productService.findAll());
        return "itemList";
    }
}
