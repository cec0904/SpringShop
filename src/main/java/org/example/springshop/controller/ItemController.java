package org.example.springshop.controller;

import org.example.springshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/itemList")
    public String itemList(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "itemList";
    }
}
