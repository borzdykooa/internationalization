package com.borzdykooa.internationalization.controller;

import com.borzdykooa.internationalization.dto.ItemDto;
import com.borzdykooa.internationalization.model.util.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getItemEn() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items")
                .param("code", "123")
                .param("language", Language.EN.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        ItemDto actualDto = objectMapper.readValue(json, ItemDto.class);

        assertEquals("Telephone", actualDto.getItemName());
        assertThat(actualDto.getAttributeNames(), containsInAnyOrder("Manufacture", "Screen Size", "Model"));
    }

    @Test
    public void getItemRu() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items")
                .param("code", "123")
                .param("language", Language.RU.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        ItemDto actualDto = objectMapper.readValue(json, ItemDto.class);

        assertEquals("Телефон", actualDto.getItemName());
        assertThat(actualDto.getAttributeNames(), containsInAnyOrder("Производитель", "Размер экрана", "Модель"));
    }
}
