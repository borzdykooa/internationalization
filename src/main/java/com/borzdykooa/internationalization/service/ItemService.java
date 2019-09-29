package com.borzdykooa.internationalization.service;

import com.borzdykooa.internationalization.dto.ItemDto;
import com.borzdykooa.internationalization.model.Item;
import com.borzdykooa.internationalization.model.ItemTranslation;
import com.borzdykooa.internationalization.model.util.Language;
import com.borzdykooa.internationalization.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemDto findByCodeAndLanguage(Integer code, Language language) {
        Item item = itemRepository.findByCode(code);

        return ItemDto.builder()
                .itemName(item.getItemTranslations().stream()
                        .filter(itemTranslation -> itemTranslation.getLanguage().equals(language))
                        .map(ItemTranslation::getName)
                        .findAny().orElse(null))
                .attributeNames(item.getAttributes().stream()
                        .map(attribute -> language.equals(Language.EN) ? attribute.getEnName() : attribute.getRuName())
                        .collect(Collectors.toList()))
                .build();
    }
}
