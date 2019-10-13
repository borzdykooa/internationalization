package com.borzdykooa.internationalization.controller;

import com.borzdykooa.internationalization.dto.ItemDto;
import com.borzdykooa.internationalization.model.util.Language;
import com.borzdykooa.internationalization.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public ItemDto getItem(@RequestParam("code") Integer code,
                           @RequestParam("language") Language language) {
        return itemService.findByCodeAndLanguage(code, language);
    }
}
